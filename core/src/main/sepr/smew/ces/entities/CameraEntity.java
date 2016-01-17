package sepr.smew.ces.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;

import sepr.smew.ces.components.*;

public class CameraEntity extends Entity {
    public CameraEntity(OrthographicCamera camera) {
        this.add(new CameraComponent(camera));
    }
}
