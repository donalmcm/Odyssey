package com.odyssey.writers;

import com.odyssey.model.Employee;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces("application/json")
public class EmployeeBodyWriter implements MessageBodyWriter<Employee> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
                               Annotation[] annotations, MediaType mediaType) {
        return type == Employee.class;
    }

    @Override
    public long getSize(Employee employee, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public void writeTo(Employee employee, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
                        OutputStream out) throws IOException, WebApplicationException {

        Writer writer = new PrintWriter(out);
        writer.write("{");
        writer.write("id: "+employee.getId());
        writer.write("first name: "+employee.getFirstName());
        writer.write("last name: "+employee.getLastName());
        writer.write("email: "+employee.getEmail());
        // call other writer classes - nested json files
        writer.write("}");


        writer.flush();
        writer.close();
    }
}
