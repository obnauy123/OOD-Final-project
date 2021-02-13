package controller;

import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;


public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private ShapeList shapeList;
    private ShapeList copyList;
    private ShapeList selectList;
    private ShapeList pastedList;
    private ShapeList outlineList;
    private ShapeList GroupList;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, ShapeList shapeList,ShapeList copyList, ShapeList selectList, ShapeList pastedList,ShapeList outlineList,ShapeList GroupList) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.shapeList = shapeList;
        this.copyList = copyList;
        this.selectList = selectList;
        this.pastedList = pastedList;
        this.outlineList = outlineList;
        this.GroupList = GroupList;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.UNDO, () -> {
            ICommand command = new UndoCommand();
            command.run();
        });
        uiModule.addEvent(EventName.REDO, () -> {
            ICommand command = new RedoCommand();
            command.run();
        });
        uiModule.addEvent(EventName.COPY, () -> {
            ICommand command = new CopyCommand(selectList, copyList);
            command.run();
        });
        uiModule.addEvent(EventName.PASTE, () -> {
            ICommand command = new PasteCommand(shapeList,copyList,pastedList);
            command.run();
        });
        uiModule.addEvent(EventName.DELETE, () -> {
            ICommand command = new DeleteCommand(selectList,shapeList,outlineList,GroupList);
            command.run();
        });
        uiModule.addEvent(EventName.GROUP, ()->{
            ICommand command = new GroupCommand(selectList,GroupList,shapeList,outlineList);
            command.run();
        });
        uiModule.addEvent(EventName.UNGROUP, ()->{
            ICommand command = new UnGroupCommand(selectList,GroupList,shapeList,outlineList);
            command.run();
        });

    }
}
