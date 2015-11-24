
import ij.IJ;
import ij.plugin.PlugIn;

public class OpenCometUAI_ implements PlugIn {

    @Override
    public void run(String string) {
        IJ.log("Welcome to OpenComet");
        OpenCometUAI.startJDialog();
    }
}
