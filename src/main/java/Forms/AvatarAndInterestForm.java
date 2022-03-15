package Forms;

import Utilities.LoadUtils;
import Utilities.RandomUtils;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AvatarAndInterestForm extends BaseForm {

    protected final String SelectAllAttrFor = "interest_selectall";
    protected final String UnSelectAllAttrFor = "interest_unselectall";
    protected AvatarAndInterestForm() {
        super(By.className("avatar-and-interests__title"), "AvatarAndInterestTitle");
    }
    private final By InterestsLabelsLocator = By.className("checkbox__label");
    private final ILabel UnselectAllLabel = getElementFactory().getLabel(By.xpath("//label[@for ='interest_unselectall']"),
            "UnselectAllCheckbox");
    private final ILink UploadButton = getElementFactory().getLink(By.className("avatar-and-interests__upload-button"),
            "uploadButton");
    private final IButton NextButton = getElementFactory().getButton(By.xpath("//button[contains(text(), 'Next')]"), "NextButton");

    private final ILabel Spinner = getElementFactory().getLabel(By.className("spinner avatar-and-interests__spinner"),
            "Spiner");

    private final By AvatarLocator = By.className("avatar-and-interests__avatar-image");

    protected void SelectRandomInterested(int NumberOfIntersts){
        List<ILabel> labelsCheckBox = getElementFactory().findElements(InterestsLabelsLocator, ElementType.LABEL);
        int numberInterest = 0;
        List<Integer> indexList = new ArrayList<Integer>();
        int iteration = 0;
        while (numberInterest != NumberOfIntersts){
            int index = RandomUtils.rndInt(0, labelsCheckBox.size()-1);
            String attr = labelsCheckBox.get(index).getAttribute("for");
            if (!Objects.equals(attr, SelectAllAttrFor) && !Objects.equals(attr, UnSelectAllAttrFor) && !indexList.contains(index)) {
                labelsCheckBox.get(index).click();
                indexList.add(index);
                numberInterest++;
            }
            iteration++;
            if (iteration == NumberOfIntersts*5){
                break;
            }
        }
    }

    protected void unselectAll() {
        UnselectAllLabel.click();
    }

    protected void loadPicture(String filePath){
        UploadButton.click();
        LoadUtils.LoadFile(filePath);
        ILabel Avatar = getElementFactory().getLabel(AvatarLocator, "avatar");
        Avatar.state().waitForDisplayed();
    }

    protected void NextButtonClick(){
        NextButton.clickAndWait();
    }
}
