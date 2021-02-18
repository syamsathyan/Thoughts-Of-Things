package com.ve.cxe.commons.ai;

import com.ve.cxe.commons.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sathyasy on 9/28/15.
 */
public class Thought implements Comparable {

    long rank;
    String thought;
    Thought parent;
    Map<String, Relation> children = new HashMap<>();

    public Thought(String thought) {
        //always child's rank is more than the parent, how more is natural to the local star cluster of child thoughts around its parent
        rank = 1;
        this.parent = null;
        this.thought = thought;
    }

    /**
     * BackPressure is a potential solution to make this efficient / fast
     *
     * @param thought
     * @param thoughtStream
     * @param index
     */
    public boolean addRelation(String thought, String thoughtStream[], int index) {
        Thought childThought;
        String relation;
        boolean backPressure = false;
        if (ThoughtFactory.relations.contains(thought)) {
            //thought itself is the relation
            relation = thought;
            childThought = new Thought(thoughtStream[index + 1]);
            backPressure = true;
        } else {
            relation = "unknown";
            childThought = new Thought(thought);
        }
        //Child Rank, Parent and other configurations
        childThought.rank = this.rank + children.size(); // not really unique and that's the point, the child rank is relative to its parent
        childThought.parent = this;
        Relation r = new Relation(relation, childThought);
        children.put(relation, r);

        return backPressure;
    }

    public class Relation {
        String relation;
        Thought thought;

        public Relation(String relation, Thought thought) {
            this.relation = relation;
            this.thought = thought;
        }

        @Override
        public String toString() {
            return Strings.joinFast("[relation:", relation, ",", this.thought.toString(), "]");
            //return Strings.joinFast("[Relation:", relation);
        }

    }

    @Override
    public String toString() {
        /*
        if (children.size() > 0) {
            return Strings.joinFast("[thought:", thought, ",rank:", String.valueOf(rank), "(children:", String.valueOf(children.size()), ")", "]");
        } else {
            return Strings.joinFast("[thought:", thought, ",rank:", String.valueOf(rank), "]");
        }
        */
        return Strings.joinFast("[thought:", thought, "]");
    }

    public String getThought() {
        return thought;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Thought) {
            Thought incoming = (Thought) o;
            return this.rank > incoming.rank ? 0 : 1;
        }
        return 0;
    }
}
