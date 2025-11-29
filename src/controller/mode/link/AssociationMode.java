package controller.mode.link;

import controller.EditorController;
import model.ConnectionPort;
import model.basic.BasicObject;
import model.link.AssociationLink;
import model.link.LinkObject;

public class AssociationMode extends LinkObjectMode {

    public AssociationMode(EditorController controller) {
        super(controller);
    }

    @Override
    protected LinkObject createLinkObject(ConnectionPort source, ConnectionPort target) {
        return new AssociationLink(source, target);
    }
}
