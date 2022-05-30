package wooter.annotation.spring.field;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

@Data
public class FieldTreeNode {

    private boolean marked;

    private Field node;

    private List<FieldTreeNode> children;


    public FieldTreeNode(Field node) {
        this.node = node;
    }

    public FieldTreeNode(Field node, List<FieldTreeNode> children) {
        this(node);
        this.children = children;
    }

    public boolean hasChildren() {
        return CollectionUtils.isNotEmpty(children);
    }

    public boolean isArray() {
        return node.getType().isArray();
    }

    public boolean isCollection() {
        return Collection.class.isAssignableFrom(node.getType());
    }

    public boolean isAnnotationPresent(Class<? extends Annotation> annotationCls) {
        return node.isAnnotationPresent(annotationCls);
    }
}
