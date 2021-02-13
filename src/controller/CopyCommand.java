package controller;

public class CopyCommand implements ICommand {
    ShapeList selectedList;
    ShapeList copyList;
    public CopyCommand(ShapeList selectedList, ShapeList copyList){
        this.selectedList = selectedList;
        this.copyList = copyList;
    }
    @Override
    public void run(){
       for(IShapeAction shape : selectedList.getShapesList()){
            copyList.add(shape);
       }
    }
}