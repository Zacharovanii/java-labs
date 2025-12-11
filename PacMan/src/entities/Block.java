package entities;

import model.Direction;
import model.GameModel;

import java.awt.*;
import java.util.HashSet;

public class Block {
    int x;
    int y;
    int width;
    int height;
    Image image;

    int startX;
    int startY;
    Direction direction = Direction.UP;
    int velocityX = 0;
    int velocityY = 0;

    Block(Image image, int x, int y, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.startX = x;
        this.startY = y;
    }

    void updateDirection(Direction direction, HashSet<Block> walls) {
        Direction prevDirection = this.direction;
        this.direction = direction;
        updateVelocity();
        this.x += this.velocityX;
        this.y += this.velocityY;
        for (Block wall : walls) {
            if (collision(this, wall)) {
                this.x -= this.velocityX;
                this.y -= this.velocityY;
                this.direction = prevDirection;
                updateVelocity();
            }
        }
    }

    public boolean collision(Block a, Block b) {
        return  a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y;
    }

    void updateVelocity() {
        int speed = GameModel.tileSize / 4;

        switch (this.direction) {
            case UP:
                this.velocityX = 0;
                this.velocityY = -speed;
                break;

            case DOWN:
                this.velocityX = 0;
                this.velocityY = speed;
                break;

            case LEFT:
                this.velocityX = -speed;
                this.velocityY = 0;
                break;

            case RIGHT:
                this.velocityX = speed;
                this.velocityY = 0;
                break;
        }
    }


    void reset() {
        this.x = this.startX;
        this.y = this.startY;
    }
}