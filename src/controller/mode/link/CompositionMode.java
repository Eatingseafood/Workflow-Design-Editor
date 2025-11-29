package controller.mode.link;

import controller.EditorController;
import model.ConnectionPort;
import model.basic.BasicObject;
import model.link.CompositionLink;
import model.link.LinkObject;

public class CompositionMode extends LinkObjectMode {

    public CompositionMode(EditorController controller) {
        super(controller);
    }

    @Override
    protected LinkObject createLinkObject(ConnectionPort source, ConnectionPort target) {
        return new CompositionLink(source, target);
    }


}
