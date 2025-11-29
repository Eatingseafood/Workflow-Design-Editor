package controller.mode.link;

import controller.EditorController;
import model.ConnectionPort;
import model.link.GeneralizationLink;
import model.link.LinkObject;

public class GeneralizationMode extends LinkObjectMode {

    public GeneralizationMode(EditorController controller) {
        super(controller);
    }

    @Override
    protected LinkObject createLinkObject(ConnectionPort source, ConnectionPort target) {
        return new GeneralizationLink(source, target);
    }
}
