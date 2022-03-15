import DB.Models.Author;
import DB.Models.Project;
import DB.Models.TestModel;
import DB.Tables.AuthorTable;
import DB.Tables.ProjectTable;
import DB.Tables.TestTable;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import testData.DBTestData;
import testData.testData2;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TestDataBase {

    public static long authorID;
    public static long projectID;
    public static long setStatusID = 2;

    @BeforeClass
    public static void startClass(){
        authorID = AuthorTable.addAuthor(new Author(DBTestData.userName, DBTestData.login, DBTestData.email));
        projectID = ProjectTable.addProject(new Project(DBTestData.projectName));
    }

    @Test
    public void TestDB(){
        List<TestModel> tests = TestTable.getAllTests();
        List<TestModel> chosenTests = new ArrayList<>();
        int numberOfTests = 0;
        for (TestModel test : tests) {
            boolean idChecker = Utils.repeatNumSearcher((int) test.getId());
            if (idChecker==true) {
                chosenTests.add(test);
                numberOfTests++;
                if (numberOfTests == testData2.numberOfTests){
                    break;
                }
            }
        }
        chosenTests.stream().forEach(testModel -> System.out.println(testModel.getId()));
        chosenTests.stream().forEach(testModel -> testModel.setProject_id(projectID));
        chosenTests.stream().forEach(testModel -> testModel.setAuthor_id(authorID));
        List<Long> chosenTestsId =  chosenTests.stream().map(TestTable::addTest).collect(Collectors.toList());
        chosenTests.stream().forEach(testModel -> testModel.setStatus_id(setStatusID));
        chosenTests.stream().forEach(TestTable::updateTest);
        List<TestModel> updateTests = chosenTestsId.stream().map(TestTable::getTestByID).collect(Collectors.toList());
        Assert.assertTrue("Test have not changed", updateTests.stream().allMatch(testModel -> testModel.getStatus_id()==setStatusID));

        chosenTestsId.stream().forEach(Long -> TestTable.deleteTestByID(Long));
        List<TestModel> deleteTests = chosenTestsId.stream().map(TestTable::getTestByID).collect(Collectors.toList());
        Assert.assertTrue("Test have not deleted", deleteTests.stream().allMatch(Objects::isNull));
    }
}

