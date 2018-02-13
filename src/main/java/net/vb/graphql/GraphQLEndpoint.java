package net.vb.graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

import javax.servlet.annotation.WebServlet;


@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

public GraphQLEndpoint() {
    super(buildSchema());
}

private static GraphQLSchema buildSchema() {
    LinkRepository linkRepository = new LinkRepository();
    return SchemaParser.newParser()
            .file("schema.graphqls")
            .resolvers(new Query(linkRepository), new Mutation(linkRepository))
            .build()
            .makeExecutableSchema();
}

}
