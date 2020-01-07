package com.poc.graalvm.error;

import com.poc.graalvm.model.ResponseData;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidMongoIdHandler implements ExceptionMapper<InvalidMongoIdException> {

    @Override
    public Response toResponse(InvalidMongoIdException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseData("Mensaje API",e.getMessage())).build();
    }
}
