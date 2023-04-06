package fr.greencodeinitiative.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.MethodMatchers;
import org.sonar.plugins.java.api.tree.Tree;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

@Rule(
        key = "CRJVM300",
        name = "Developpement",
        description = ForceDataPaginationWithHibernate.MESSAGE_RULE,
        priority = Priority.MINOR,
        tags = {"bug"})
public class ForceDataPaginationWithHibernate extends IssuableSubscriptionVisitor {

    protected static final String MESSAGE_RULE = "Force data pagination with hibernate";

    private final MethodMatchers EXECUTE_METHOD = MethodMatchers.or(
            MethodMatchers.create().ofSubTypes("java.sql.Statement").names("executeUpdate")
                    .withAnyParameters().build());

    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.METHOD_INVOCATION);
    }


    @Override
    public void visitNode(@Nonnull Tree tree) {


    }


}
