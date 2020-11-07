package starpew;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;

public class StarpewFactory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data){

        return FXGL.entityBuilder()
                .type(StarpewType.PLAYER)
                .from(data)
                .bbox(new  HitBox(BoundingShape.box(267 / 4, 400 / 4)))
                .with(new CollidableComponent(true))
                .with(new AnimationComponent())
                .build();
    } 
    
}
