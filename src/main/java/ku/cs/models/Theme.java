package ku.cs.models;

public class Theme {
    private boolean isLightMode;

    public Theme() {
        isLightMode = true;
    }

    public boolean isLightMode() {
        return isLightMode;
    }

    public void setLightMode(boolean lightMode) {
        isLightMode = lightMode;
    }

    @Override
    public String toString() {
        return String.valueOf(isLightMode);
    }
}
