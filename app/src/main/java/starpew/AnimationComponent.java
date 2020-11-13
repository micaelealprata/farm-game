package starpew;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.geometry.Point2D;
import javafx.util.Duration;

public class AnimationComponent extends Component {

    private int speed = 0;

    private boolean isMovingX = false;
    private boolean isMovingDown = false;
    private boolean isMovingUp = false;

    private AnimatedTexture texture;
    private AnimationChannel animIdle, animWalk, animWalkUp, animWalkDown;

    public AnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image("Charactervector.png"), 4, 267 / 4, 100, Duration.seconds(1), 0, 0);

        animWalk = new AnimationChannel(FXGL.image("Charactervector.png"), 4, 267 / 4, 100, Duration.seconds(1), 12,
                15);
        animWalkUp = new AnimationChannel(FXGL.image("Charactervector.png"), 4, 267 / 4, 100, Duration.seconds(1), 4,
                7);
        animWalkDown = new AnimationChannel(FXGL.image("Charactervector.png"), 4, 267 / 4, 100, Duration.seconds(1), 0,
                3);

        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        if (speed != 0) {
            if (isMovingX) {
                entity.translateX(speed * tpf);
                if (texture.getAnimationChannel() == animIdle || texture.getAnimationChannel() == animWalkDown
                        || texture.getAnimationChannel() == animWalkUp) {
                    texture.loopAnimationChannel(animWalk);
                }
                isMovingX = false;
            }

            if (isMovingDown) {
                entity.translateY(speed * tpf);
                if (texture.getAnimationChannel() == animIdle || texture.getAnimationChannel() == animWalk
                        || texture.getAnimationChannel() == animWalkUp) {
                    texture.loopAnimationChannel(animWalkDown);
                }
                isMovingDown = false;
            }

            if (isMovingUp) {
                entity.translateY(speed * tpf);
                if (texture.getAnimationChannel() == animIdle || texture.getAnimationChannel() == animWalk
                        || texture.getAnimationChannel() == animWalkDown) {
                    texture.loopAnimationChannel(animWalkUp);
                }
                isMovingUp = false;
            }
        }

        if (speed == 0) {
            texture.loopAnimationChannel(animIdle);
        }
    }

    public void moveRight() {
        getEntity().setScaleX(1);
        speed = 150;
        isMovingX = true;
    }

    public void moveLeft() {
        getEntity().setScaleX(-1);
        speed = -150;
        isMovingX = true;
    }

    public void moveDown() {
        speed = 150;
        isMovingDown = true;
    }

    public void moveUp() {
        speed = -150;
        isMovingUp = true;
    }

    public void stop() {
        speed = 0;
    }
}