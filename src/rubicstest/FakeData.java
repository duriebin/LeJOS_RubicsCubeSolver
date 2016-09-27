package rubicstest;

import java.util.ArrayList;

import rubicscube.Corner;
import rubicscube.Cube;
import rubicscube.CubeLayer;
import rubicscube.CubePosition;
import rubicscube.Edge;
import rubicscube.LayerNotAllowedException;
import rubicscube.Middle;
import rubicscube.PositionNotAllowedException;

public class FakeData {
	
	public static ArrayList<float[]> getFakeColorsCube5() {
		ArrayList<float[]> result = new ArrayList<>();
		result.add(new float[] { 0.36490196f, 0.28941175f, 0.29647058f });
		result.add(new float[] { 0.04372549f, 0.09039216f, 0.053333335f });
		result.add(new float[] { 0.36117646f, 0.29490197f, 0.2972549f });
		result.add(new float[] { 0.17450981f, 0.07019608f, 0.04235294f });
		result.add(new float[] { 0.040784314f, 0.06843138f, 0.037254903f });
		result.add(new float[] { 0.36058822f, 0.3009804f, 0.3072549f });
		result.add(new float[] { 0.04901961f, 0.088235304f, 0.04647059f });
		result.add(new float[] { 0.23588236f, 0.18078431f, 0.11882353f });
		result.add(new float[] { 0.34823528f, 0.28784314f, 0.29490194f });

		result.add(new float[] { 0.18235295f, 0.06882353f, 0.051568627f });
		result.add(new float[] { 0.3537255f, 0.30156863f, 0.3035294f });
		result.add(new float[] { 0.04764706f, 0.090588234f, 0.04627451f });
		result.add(new float[] { 0.23980391f, 0.18921569f, 0.117843136f });
		result.add(new float[] { 0.17431374f, 0.0654902f, 0.043921567f });
		result.add(new float[] { 0.17039217f, 0.070196085f, 0.04372549f });
		result.add(new float[] { 0.17666666f, 0.067450985f, 0.04117647f });
		result.add(new float[] { 0.17039216f, 0.06921569f, 0.048235293f });
		result.add(new float[] { 0.17039217f, 0.1082353f, 0.05058824f });

		result.add(new float[] { 0.26196077f, 0.18294118f, 0.11705883f });
		result.add(new float[] { 0.24294117f, 0.18411765f, 0.121568635f });
		result.add(new float[] { 0.14784314f, 0.062156864f, 0.040784314f });
		result.add(new float[] { 0.17254904f, 0.06980392f, 0.048235293f });
		result.add(new float[] { 0.1972549f, 0.117843136f, 0.04862745f });
		result.add(new float[] { 0.03745098f, 0.06823529f, 0.1172549f });
		result.add(new float[] { 0.2337255f, 0.17294118f, 0.1154902f });
		result.add(new float[] { 0.19117646f, 0.11823529f, 0.049607843f });
		result.add(new float[] { 0.03647059f, 0.058431376f, 0.104901955f });

		result.add(new float[] { 0.20745096f, 0.121764705f, 0.051960785f });
		result.add(new float[] { 0.18980393f, 0.1209804f, 0.047058824f });
		result.add(new float[] { 0.18941177f, 0.11313726f, 0.046666667f });
		result.add(new float[] { 0.19294117f, 0.1227451f, 0.047450982f });
		result.add(new float[] { 0.24901962f, 0.1764706f, 0.11490196f });
		result.add(new float[] { 0.039803922f, 0.06411765f, 0.099803925f });
		result.add(new float[] { 0.28411764f, 0.23470588f, 0.27019608f });
		result.add(new float[] { 0.3494118f, 0.29588234f, 0.29725492f });
		result.add(new float[] { 0.23156862f, 0.17980394f, 0.12352942f });
		result.add(new float[] { 0.038235296f, 0.06333333f, 0.035686277f });
		result.add(new float[] { 0.2456863f, 0.1890196f, 0.12725492f });
		result.add(new float[] { 0.037843138f, 0.075686276f, 0.044509806f });
		result.add(new float[] { 0.04490196f, 0.09607843f, 0.051568627f });
		result.add(new float[] { 0.17705883f, 0.06784314f, 0.04509804f });
		result.add(new float[] { 0.041960783f, 0.093921565f, 0.050784312f });
		result.add(new float[] { 0.20156863f, 0.11921569f, 0.048039217f });
		result.add(new float[] { 0.0427451f, 0.08921569f, 0.046666667f });
		result.add(new float[] { 0.029607844f, 0.048431374f, 0.09039216f });
		result.add(new float[] { 0.028039217f, 0.04647059f, 0.08666667f });
		result.add(new float[] { 0.034705885f, 0.05764706f, 0.11431372f });
		result.add(new float[] { 0.3472549f, 0.28882354f, 0.29803923f });
		result.add(new float[] { 0.35019606f, 0.2937255f, 0.3072549f });
		result.add(new float[] { 0.021372551f, 0.033725493f, 0.06803922f });
		result.add(new float[] { 0.17803922f, 0.114117645f, 0.046078432f });
		result.add(new float[] { 0.2390196f, 0.1747059f, 0.123725496f });
		result.add(new float[] { 0.035686277f, 0.06627452f, 0.10941176f });
		result.add(new float[] { 0.03882353f, 0.062352944f, 0.10784314f });
		return result;
	}
	
	public static ArrayList<float[]> getFakeColorsCube4() {
		ArrayList<float[]> result = new ArrayList<>();
		result.add(new float[] { 0.11254902f, 0.06156863f, 0.038235296f });
		result.add(new float[] { 0.19686274f, 0.12372549f, 0.04588235f });
		
		
		result.add(new float[] { 0.36980397f, 0.29725492f, 0.2998039f });
		result.add(new float[] { 0.045686275f, 0.09509804f, 0.05294118f });
		result.add(new float[] { 0.35274512f, 0.2892157f, 0.28862748f });
		result.add(new float[] { 0.15862745f, 0.065882355f, 0.04254902f });
		result.add(new float[] { 0.04490196f, 0.08411765f, 0.046862744f });
		result.add(new float[] { 0.35254902f, 0.29117647f, 0.31039214f });
		result.add(new float[] { 0.029215688f, 0.048823528f, 0.030392159f });
		result.add(new float[] { 0.23078433f, 0.18137255f, 0.11686274f });
		result.add(new float[] { 0.36176473f, 0.29352945f, 0.29137254f });
		
		result.add(new float[] { 0.35784316f, 0.3035294f, 0.30215687f });
		result.add(new float[] { 0.03529412f, 0.06901961f, 0.037843138f });
		result.add(new float[] { 0.23588236f, 0.1817647f, 0.12078432f });
		result.add(new float[] { 0.124313734f, 0.05352942f, 0.03745098f });
		result.add(new float[] { 0.1664706f, 0.07058824f, 0.043137256f });
		result.add(new float[] { 0.15372549f, 0.055686276f, 0.035686277f });
		result.add(new float[] { 0.16313724f, 0.06764706f, 0.048039217f });
		result.add(new float[] { 0.19686276f, 0.120000005f, 0.051764708f });
		result.add(new float[] { 0.24254903f, 0.17137255f, 0.111372545f });
		result.add(new float[] { 0.23588236f, 0.18215686f, 0.11823529f });
		result.add(new float[] { 0.17f, 0.06156863f, 0.040784314f });
		result.add(new float[] { 0.17196079f, 0.06725491f, 0.05058824f });
		result.add(new float[] { 0.17686275f, 0.11196078f, 0.04862745f });
		result.add(new float[] { 0.027843138f, 0.0472549f, 0.081372544f });
		result.add(new float[] { 0.22901961f, 0.17490196f, 0.11215687f });
		result.add(new float[] { 0.1837255f, 0.1172549f, 0.048235293f });
		result.add(new float[] { 0.011764706f, 0.0064705885f, 0.024313726f });
		result.add(new float[] { 0.18932462f, 0.11198257f, 0.04553377f });
		
		result.add(new float[] { 0.14313726f, 0.0882353f, 0.039215688f });
		result.add(new float[] { 0.18764706f, 0.117450975f, 0.04901961f });
		result.add(new float[] { 0.2482353f, 0.18117647f, 0.12058823f });
		result.add(new float[] { 0.033725493f, 0.06392157f, 0.11117647f });
		result.add(new float[] { 0.35215688f, 0.28941175f, 0.29607844f });
		result.add(new float[] { 0.33254904f, 0.2837255f, 0.2807843f });
		result.add(new float[] { 0.23803921f, 0.18215686f, 0.1254902f });
		result.add(new float[] { 0.04f, 0.058823533f, 0.030980393f });
		result.add(new float[] { 0.24921569f, 0.19254902f, 0.12764707f });
		result.add(new float[] { 0.04372549f, 0.087254904f, 0.04627451f });
		result.add(new float[] { 0.039411765f, 0.08568628f, 0.04490196f });
		result.add(new float[] { 0.16352943f, 0.0654902f, 0.04490196f });
		result.add(new float[] { 0.042156864f, 0.09235294f, 0.051568627f });
		result.add(new float[] { 0.16921571f, 0.10294118f, 0.043333333f });
		result.add(new float[] { 0.0427451f, 0.09235294f, 0.05f });
		result.add(new float[] { 0.024705883f, 0.04156863f, 0.07862745f });
		result.add(new float[] { 0.025490198f, 0.043529414f, 0.07941177f });
		result.add(new float[] { 0.03607843f, 0.06509804f, 0.11960784f });
		result.add(new float[] { 0.35764706f, 0.2901961f, 0.28843138f });
		result.add(new float[] { 0.35411766f, 0.29470587f, 0.3072549f });
		result.add(new float[] { 0.03176471f, 0.048823528f, 0.090588234f });
		result.add(new float[] { 0.18568628f, 0.11607844f, 0.047843136f });
		result.add(new float[] { 0.24235293f, 0.18372549f, 0.12823531f });
		result.add(new float[] { 0.035882354f, 0.06803922f, 0.11686275f });
		result.add(new float[] { 0.037058823f, 0.063137256f, 0.11196079f });
		return result;
	}
	
	public static ArrayList<float[]> getFakeColorsCube3() {
		ArrayList<float[]> result = new ArrayList<>();	
		result.add(new float[] { 0.117843136f, 0.060196083f, 0.040784314f });
		
		
		result.add(new float[] { 0.039803922f, 0.06627451f, 0.1227451f });
		result.add(new float[] { 0.03764706f, 0.064117655f, 0.12117647f });
		result.add(new float[] { 0.35882348f, 0.2992157f, 0.3039216f });
		result.add(new float[] { 0.35019606f, 0.29235294f, 0.31058824f });
		result.add(new float[] { 0.027058825f, 0.04764706f, 0.081764705f });
		result.add(new float[] { 0.1837255f, 0.11647059f, 0.048823528f });
		result.add(new float[] { 0.25627452f, 0.19352941f, 0.13078432f });
		result.add(new float[] { 0.038627453f, 0.07039216f, 0.11588236f });
		result.add(new float[] { 0.030784314f, 0.047450982f, 0.08411764f });
		result.add(new float[] { 0.21980393f, 0.124313734f, 0.052156866f });
		result.add(new float[] { 0.36117643f, 0.30392155f, 0.30235296f });
		result.add(new float[] { 0.24176471f, 0.18568628f, 0.12627451f });
		result.add(new float[] { 0.18333334f, 0.12039216f, 0.047843136f });
		result.add(new float[] { 0.18568628f, 0.115686275f, 0.049411766f });
		result.add(new float[] { 0.19254902f, 0.12215686f, 0.05058824f });
		result.add(new float[] { 0.2437255f, 0.1747059f, 0.11627451f });
		result.add(new float[] { 0.038627453f, 0.07019608f, 0.11529412f });
		result.add(new float[] { 0.35470587f, 0.29176474f, 0.30294117f });
		result.add(new float[] { 0.039019607f, 0.07176471f, 0.041960783f });
		result.add(new float[] { 0.24921569f, 0.18921569f, 0.12784314f });
		result.add(new float[] { 0.048235293f, 0.09372549f, 0.051764708f });
		result.add(new float[] { 0.046666667f, 0.09627451f, 0.05352941f });
		result.add(new float[] { 0.14627452f, 0.06666667f, 0.04647059f });
		result.add(new float[] { 0.043333333f, 0.09117647f, 0.049607843f });
		result.add(new float[] { 0.11921569f, 0.0754902f, 0.03254902f });
		result.add(new float[] { 0.04509804f, 0.09470588f, 0.048431374f });
		result.add(new float[] { 0.022941178f, 0.03627451f, 0.07098039f });
		result.add(new float[] { 0.09921569f, 0.052352943f, 0.033725493f });
		result.add(new float[] { 0.23156862f, 0.18058825f, 0.12058823f });
		
		result.add(new float[] { 0.16784315f, 0.07215686f, 0.046078432f });
		result.add(new float[] { 0.17784315f, 0.063725494f, 0.039607845f });
		result.add(new float[] { 0.1709804f, 0.07372549f, 0.04647059f });
		result.add(new float[] { 0.17313728f, 0.1072549f, 0.046862744f });
		result.add(new float[] { 0.35941178f, 0.30784315f, 0.30411762f });
		result.add(new float[] { 0.040392157f, 0.071960784f, 0.039607845f });
		result.add(new float[] { 0.2517647f, 0.17882352f, 0.11529412f });
		result.add(new float[] { 0.24039216f, 0.18803921f, 0.12117647f });
		result.add(new float[] { 0.16686276f, 0.06686275f, 0.04509804f });
		result.add(new float[] { 0.17235294f, 0.06941177f, 0.051960785f });
		result.add(new float[] { 0.17137256f, 0.11137255f, 0.04627451f });
		result.add(new float[] { 0.03647059f, 0.063137256f, 0.10254902f });
		result.add(new float[] { 0.22823529f, 0.17568628f, 0.11490196f });
		result.add(new float[] { 0.1937255f, 0.12058823f, 0.0472549f });
		result.add(new float[] { 0.01254902f, 0.006666667f, 0.02490196f });
		result.add(new float[] { 0.36431372f, 0.2835294f, 0.28137255f });
		result.add(new float[] { 0.05117647f, 0.100980386f, 0.054901965f });
		result.add(new float[] { 0.35999998f, 0.29647058f, 0.29764706f });
		result.add(new float[] { 0.1717647f, 0.074705884f, 0.047058824f });
		result.add(new float[] { 0.035490196f, 0.061764706f, 0.035686277f });
		result.add(new float[] { 0.30882353f, 0.2617647f, 0.27392158f });
		result.add(new float[] { 0.03647059f, 0.07588236f, 0.04f });
		result.add(new float[] { 0.22058824f, 0.17705883f, 0.114509806f });
		result.add(new float[] { 0.3652941f, 0.2998039f, 0.2954902f });
		return result;
	}
	
	public static ArrayList<float[]> getFakeColorsCube2() {
		ArrayList<float[]> result = new ArrayList<>();		
		result.add(new float[] { 0.3672549f, 0.29137257f, 0.2960784f });
		result.add(new float[] { 0.05f, 0.097058825f, 0.05745098f });
		result.add(new float[] { 0.35137254f, 0.29313725f, 0.30078432f });
		result.add(new float[] { 0.17313726f, 0.07725491f, 0.047058824f });
		result.add(new float[] { 0.049411766f, 0.084901966f, 0.045686275f });
		result.add(new float[] { 0.3590196f, 0.30254903f, 0.3082353f });
		result.add(new float[] { 0.05019608f, 0.08294118f, 0.043333333f });
		result.add(new float[] { 0.23745099f, 0.18568628f, 0.11941177f });
		result.add(new float[] { 0.36411765f, 0.29784316f, 0.29568627f });
		result.add(new float[] { 0.17509803f, 0.07313726f, 0.05392157f });
		result.add(new float[] { 0.35313728f, 0.3f, 0.30509806f });
		result.add(new float[] { 0.047450982f, 0.092745095f, 0.05058824f });
		result.add(new float[] { 0.23607843f, 0.18431373f, 0.12019608f });
		result.add(new float[] { 0.17784314f, 0.06843137f, 0.045686275f });
		result.add(new float[] { 0.14745098f, 0.06764706f, 0.040392157f });
		result.add(new float[] { 0.11039217f, 0.043529414f, 0.029215688f });
		result.add(new float[] { 0.1717647f, 0.07333334f, 0.04627451f });
		result.add(new float[] { 0.14039215f, 0.09705882f, 0.04588235f });
		result.add(new float[] { 0.2527451f, 0.18f, 0.11921569f });
		result.add(new float[] { 0.23333332f, 0.18156864f, 0.12156862f });
		result.add(new float[] { 0.15411766f, 0.06666667f, 0.04490196f });
		result.add(new float[] { 0.16019608f, 0.06784315f, 0.051960785f });
		result.add(new float[] { 0.19294119f, 0.11607844f, 0.04862745f });
		result.add(new float[] { 0.030980393f, 0.05686275f, 0.09470588f });
		result.add(new float[] { 0.22039215f, 0.1682353f, 0.10078432f });
		result.add(new float[] { 0.19058824f, 0.11960784f, 0.047450982f });
		result.add(new float[] { 0.022352943f, 0.03392157f, 0.068627454f });
		result.add(new float[] { 0.23000002f, 0.12862745f, 0.054509807f });
		result.add(new float[] { 0.19411764f, 0.12196078f, 0.047843136f });
		result.add(new float[] { 0.18215688f, 0.11627451f, 0.050784312f });
		result.add(new float[] { 0.19058824f, 0.12019608f, 0.04980392f });
		result.add(new float[] { 0.23921569f, 0.17529413f, 0.117843136f });
		result.add(new float[] { 0.033137254f, 0.062156867f, 0.10294118f });
		result.add(new float[] { 0.35313725f, 0.28745097f, 0.2982353f });
		result.add(new float[] { 0.36490196f, 0.30568627f, 0.30254903f });
		result.add(new float[] { 0.24294119f, 0.18294118f, 0.12607843f });
		result.add(new float[] { 0.04235294f, 0.060784318f, 0.030588236f });
		result.add(new float[] { 0.25058824f, 0.18960783f, 0.12862746f });
		result.add(new float[] { 0.039607845f, 0.078039214f, 0.043333333f });
		result.add(new float[] { 0.04764706f, 0.09745099f, 0.053333335f });
		result.add(new float[] { 0.14921568f, 0.0672549f, 0.046078432f });
		result.add(new float[] { 0.04588235f, 0.10019608f, 0.052352943f });
		result.add(new float[] { 0.19941178f, 0.11921569f, 0.049607843f });
		result.add(new float[] { 0.04235294f, 0.089999996f, 0.04627451f });
		result.add(new float[] { 0.037058823f, 0.05627451f, 0.09901961f });
		result.add(new float[] { 0.04098039f, 0.06588236f, 0.121568635f });
		result.add(new float[] { 0.03627451f, 0.060000002f, 0.11607844f });
		result.add(new float[] { 0.35117644f, 0.29294115f, 0.3009804f });
		result.add(new float[] { 0.35235292f, 0.29411763f, 0.31f });
		result.add(new float[] { 0.029215688f, 0.051176477f, 0.090980396f });
		result.add(new float[] { 0.17431374f, 0.1137255f, 0.047450982f });
		result.add(new float[] { 0.22607844f, 0.16921568f, 0.12196078f });
		result.add(new float[] { 0.038431372f, 0.06921569f, 0.11509804f });
		result.add(new float[] { 0.04156863f, 0.059215687f, 0.099803925f });
		return result;
	}
	
	public static ArrayList<float[]> getFakeColorsCube() {
		ArrayList<float[]> result = new ArrayList<>();		
		
		result.add(new float[] { 0.36745095f, 0.29705882f, 0.30058825f });
		result.add(new float[] { 0.048431374f, 0.094901964f, 0.053725492f });
		result.add(new float[] { 0.3662745f, 0.30196077f, 0.2998039f });
		result.add(new float[] { 0.16019608f, 0.06960785f, 0.04490196f });
		result.add(new float[] { 0.03254902f, 0.061960787f, 0.03509804f });
		result.add(new float[] { 0.35392156f, 0.2982353f, 0.30588236f });
		result.add(new float[] { 0.0472549f, 0.079607844f, 0.043137256f });
		result.add(new float[] { 0.2354902f, 0.18215688f, 0.11588235f });
		result.add(new float[] { 0.36137253f, 0.28960782f, 0.2856863f });

		result.add(new float[] { 0.18447714f, 0.0753268f, 0.05490196f });
		result.add(new float[] { 0.3529412f, 0.3027451f, 0.30215687f });
		result.add(new float[] { 0.03882353f, 0.07098039f, 0.040588237f });
		result.add(new float[] { 0.22941177f, 0.17372549f, 0.12058823f });
		result.add(new float[] { 0.17039216f, 0.07156863f, 0.047450982f });
		result.add(new float[] { 0.16686276f, 0.07254903f, 0.04411765f });
		result.add(new float[] { 0.17313728f, 0.0627451f, 0.037254903f });
		result.add(new float[] { 0.17352942f, 0.07627451f, 0.047450982f });
		result.add(new float[] { 0.18058825f, 0.11313726f, 0.049411766f });

		result.add(new float[] { 0.25f, 0.1764706f, 0.114117645f });
		result.add(new float[] { 0.23843138f, 0.18450981f, 0.11941177f });
		result.add(new float[] { 0.11960785f, 0.05137255f, 0.03627451f });
		result.add(new float[] { 0.16431373f, 0.064313725f, 0.050784312f });
		result.add(new float[] { 0.1864706f, 0.11254902f, 0.046666667f });
		result.add(new float[] { 0.03666667f, 0.06960785f, 0.11156863f });
		result.add(new float[] { 0.23882353f, 0.18196079f, 0.11176471f });
		result.add(new float[] { 0.18784313f, 0.117450975f, 0.047058824f });
		result.add(new float[] { 0.01627451f, 0.025686275f, 0.057254903f });

		result.add(new float[] { 0.19681372f, 0.11703432f, 0.04828432f });
		result.add(new float[] { 0.19431373f, 0.122352935f, 0.046078432f });
		result.add(new float[] { 0.14607844f, 0.09941177f, 0.043137256f });
		result.add(new float[] { 0.1827451f, 0.117450975f, 0.04764706f });
		result.add(new float[] { 0.24117646f, 0.17627451f, 0.11627451f });
		result.add(new float[] { 0.038235296f, 0.07058824f, 0.1145098f });
		result.add(new float[] { 0.32254905f, 0.25882354f, 0.28000003f });
		result.add(new float[] { 0.35725492f, 0.30313724f, 0.29901963f });
		result.add(new float[] { 0.25470588f, 0.19235294f, 0.12588236f });

		result.add(new float[] { 0.03627451f, 0.05705883f, 0.029803922f });
		result.add(new float[] { 0.24882352f, 0.18784313f, 0.12686275f });
		result.add(new float[] { 0.048039217f, 0.08843137f, 0.04862745f });
		result.add(new float[] { 0.045490198f, 0.09392157f, 0.05137255f });
		result.add(new float[] { 0.15392157f, 0.06509804f, 0.04647059f });
		result.add(new float[] { 0.04254902f, 0.08705883f, 0.04980392f });
		result.add(new float[] { 0.05509804f, 0.03019608f, 0.016078431f });
		result.add(new float[] { 0.043921567f, 0.093333334f, 0.047450982f });
		result.add(new float[] { 0.029215688f, 0.045490198f, 0.084901966f });

		result.add(new float[] { 0.021764707f, 0.03666667f, 0.06607844f });
		result.add(new float[] { 0.037254903f, 0.06509804f, 0.11862745f });
		result.add(new float[] { 0.35999998f, 0.30254903f, 0.30235296f });
		result.add(new float[] { 0.3519608f, 0.29509804f, 0.30588236f });
		result.add(new float[] { 0.018431375f, 0.03176471f, 0.06333333f });
		result.add(new float[] { 0.16509804f, 0.10980393f, 0.048823528f });
		result.add(new float[] { 0.12039216f, 0.08980393f, 0.0754902f });
		result.add(new float[] { 0.032352943f, 0.063725494f, 0.10235294f });
		result.add(new float[] { 0.034509804f, 0.05411765f, 0.0927451f });
		
		return result;
	}
	
	/*
	 * Initialisiert einen Cube mit plausiblen Fakedaten
	 */
	public static Cube getFakeCube() {
		Cube result = new Cube();
		try {
			// 8 Ecken
			result.setCubeFragment(CubeLayer.TOP, CubePosition.LEFTFRONT, new Corner(CubeLayer.TOP, CubePosition.LEFTFRONT, 6, 4, 1));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.RIGHTFRONT, new Corner(CubeLayer.TOP, CubePosition.RIGHTFRONT, 6, 5, 1));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.LEFTBACK, new Corner(CubeLayer.TOP, CubePosition.LEFTBACK, 4, 1, 3));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.RIGHTBACK, new Corner(CubeLayer.TOP, CubePosition.RIGHTBACK, 4, 6, 2));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.LEFTFRONT, new Corner(CubeLayer.BOTTOM, CubePosition.LEFTFRONT, 3, 5, 2));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.RIGHTFRONT, new Corner(CubeLayer.BOTTOM, CubePosition.RIGHTFRONT, 2, 3, 4));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.LEFTBACK, new Corner(CubeLayer.BOTTOM, CubePosition.LEFTBACK, 5, 2, 6));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.RIGHTBACK, new Corner(CubeLayer.BOTTOM, CubePosition.RIGHTBACK, 1, 5, 3));

			// 61 Kanten
			result.setCubeFragment(CubeLayer.TOP, CubePosition.LEFTMIDDLE, new Edge(CubeLayer.TOP, CubePosition.LEFTMIDDLE, 3, 4));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.FRONTMIDDLE, new Edge(CubeLayer.TOP, CubePosition.FRONTMIDDLE, 4, 1));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.RIGHTMIDDLE, new Edge(CubeLayer.TOP, CubePosition.RIGHTMIDDLE, 1, 6));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.BACKMIDDLE, new Edge(CubeLayer.TOP, CubePosition.BACKMIDDLE, 6, 2));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.LEFTFRONT, new Edge(CubeLayer.MIDDLE, CubePosition.LEFTFRONT, 1, 3));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.RIGHTFRONT, new Edge(CubeLayer.MIDDLE, CubePosition.RIGHTFRONT, 3, 2));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.LEFTBACK, new Edge(CubeLayer.MIDDLE, CubePosition.LEFTBACK, 6, 4));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.RIGHTBACK, new Edge(CubeLayer.MIDDLE, CubePosition.RIGHTBACK, 2, 5));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.LEFTMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.LEFTMIDDLE, 2, 4));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.FRONTMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.FRONTMIDDLE, 5, 6));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.RIGHTMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.RIGHTMIDDLE, 1, 5));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.BACKMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.BACKMIDDLE, 3, 5));

			// 5 Mitten
			result.setCubeFragment(CubeLayer.TOP, CubePosition.MIDDLE, new Middle(CubeLayer.TOP, CubePosition.MIDDLE, 6));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.MIDDLE, new Middle(CubeLayer.BOTTOM, CubePosition.MIDDLE, 3));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.LEFTMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.LEFTMIDDLE, 4));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.FRONTMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.FRONTMIDDLE, 1));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.RIGHTMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.RIGHTMIDDLE, 5));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.BACKMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.BACKMIDDLE, 2));
			
		} catch (PositionNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LayerNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
