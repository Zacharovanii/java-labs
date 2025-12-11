package model;

public enum GhostMode {
    SCATTER,    // Режим разброса - идет в свой угол
    CHASE,      // Режим преследования
    FRIGHTENED, // Испуганный режим (синий, убегает)
    EYES        // Режим "только глаза" - возвращается в дом
}