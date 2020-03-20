package deadwood.mvc;

public abstract class View {
    // Private attribute
    protected String displayString;

    // Getter
    public String getDisplayString() {
        return displayString;
    }

    // Public methods
    public abstract void refreshView();

    public void display() { System.out.println(displayString); }
}
