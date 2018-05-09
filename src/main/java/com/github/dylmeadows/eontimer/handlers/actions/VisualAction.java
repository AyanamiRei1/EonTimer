package com.github.dylmeadows.eontimer.handlers.actions;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A visual action capable of momentarily changing the background fill of a
 * {@link Region} during a timer countdown. Contains a reference to the
 * {@link Region}, whose background fill will be altered and to the
 * {@link Color} that it will change to.
 */
public class VisualAction implements ICountdownAction {

    /**
     * The fill being altered by the running action.
     */
    private final ReadOnlyObjectWrapper<Paint> fill;

    /**
     * The default fill to used when not flashing.
     */
    private final ObjectProperty<Paint> defaultFill;

    /**
     * The active fill to flash.
     */
    private final ObjectProperty<Paint> activeFill;

    /**
     * Thread to submit tasks to.
     */
    private final ExecutorService service = Executors.newSingleThreadExecutor();

    /**
     * Initializes the visual action.
     */
    public VisualAction() {
        this.fill = new ReadOnlyObjectWrapper<>(Color.TRANSPARENT);
        this.defaultFill = new SimpleObjectProperty<>(Color.TRANSPARENT);
        this.activeFill = new SimpleObjectProperty<>(Color.BLACK);
    }

    /**
     * Momentarily (50ms) alters the background fill.
     */
    @Override
    public void action() {
        service.submit(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                fill.set(getActiveFill());
                TimeUnit.MILLISECONDS.sleep(50);
                fill.set(getDefaultFill());
                return null;
            }
        });
    }

    /**
     * @return see {@link #fill}
     */
    public Paint getFill() {
        return fill.get();
    }

    /**
     * @return see {@link #fill}
     */
    public ReadOnlyObjectProperty<Paint> fillProperty() {
        return fill.getReadOnlyProperty();
    }

    public Paint getDefaultFill() {
        return defaultFill.get();
    }

    public ObjectProperty<Paint> defaultFillProperty() {
        return defaultFill;
    }

    public void setDefaultFill(Paint defaultFill) {
        this.defaultFill.set(defaultFill);
    }

    public Paint getActiveFill() {
        return activeFill.get();
    }

    public ObjectProperty<Paint> activeFillProperty() {
        return activeFill;
    }

    public void setActiveFill(Paint activeFill) {
        this.activeFill.set(activeFill);
    }
}
