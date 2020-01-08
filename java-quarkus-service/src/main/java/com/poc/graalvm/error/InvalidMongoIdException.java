package com.poc.graalvm.error;

import java.io.Serializable;

public class InvalidMongoIdException extends RuntimeException implements Serializable {

    public InvalidMongoIdException() {
        super("Estructura de Id invalido o nulo");
    }

    public InvalidMongoIdException(String message) {
        super(message);
    }
}
