package com.flifi.tot.commons.ai;

import com.flifi.tot.commons.util.StringUtils;

public class Relation {
    String relation;
    Thought thought;

    public Relation(String relation, Thought thought) {
        this.relation = relation;
        this.thought = thought;
    }

    @Override
    public String toString() {
        return StringUtils.joinFast("[relation:", relation, ",", this.thought.toString(), "]");
        //return Strings.joinFast("[Relation:", relation);
    }

}
