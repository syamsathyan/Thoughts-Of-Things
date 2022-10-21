package com.ve.cxe.commons.ai;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sathyasy on 9/28/15.
 */
public class ThoughtFactory {

    static Map<String, Thought> thoughts = new HashMap<>();
    static ArrayList<String> relations = new ArrayList<>();

    private static final void seedPrepositions() {
        relations.add("is");
        relations.add("to");
    }

    public static Thought findContextRoot(String[] thoughtStream, int index) {
        if (index == 0) {
            return null;
        }
        Thought t;
        for (int i = 0; i < index; i++) {
            t = thoughts.get(thoughtStream[i]);
            if (!isRelation(t.thought)) {
                return t;
            }
        }
        return null;
    }

    public static void think(String thoughtStream[]) {
        //think(processPreposition(thought));
        boolean backPressure = false;
        String thought;
        for (int i = 0; i < thoughtStream.length; i++) {
            thought = thoughtStream[i];
            if (isRelation(thought)) {
                //Prevent Adding Relation as parent
                //Instead add as a child
                Thought parent = findContextRoot(thoughtStream, i);
                if (parent != null) {
                    //Prevent NPE
                    backPressure = parent.linkThought(thought, thoughtStream, i);
                    //Process Back-Pressure
                    if (backPressure) {
                        i++;
                    } else {
                        //NTDO
                    }
                }
            } else {
                //always a word / letter
                Thought parent = thoughts.get(thought);
                if (parent == null) {
                    Thought t = new Thought(thought);
                    thoughts.put(thought, t);
                } else {
                    backPressure = parent.linkThought(thought, thoughtStream, i);
                    //Process Back-Pressure
                    if (backPressure) {
                        i++;
                    } else {
                        //NTDO
                    }
                }
            }
        }
    }

    private static boolean isRelation(String thought) {
        return relations.contains(thought) ? true : false;
    }

    public static void process(String thought) {
        think(segment(thought));
    }

    public List<String> getThoughts() {
        return thoughts.values().parallelStream().map(thought -> thought.thought).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<String> getThoughtParents() {
        return thoughts.values().parallelStream().filter(thought1 -> thought1.rank == 1).map(thought -> thought.thought).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<String> getThoughtWithChildren() {
        return thoughts.values().parallelStream().filter(thought1 -> thought1.children.size() > 0 && thought1.rank == 1)
                .map(thought -> thought.thought).collect(Collectors.toCollection(ArrayList::new));
    }

    public static void main(String args[]) {
        seedPrepositions();
        process("apple");
        process("Apple is red");

        System.out.println(thoughts.toString());
    }

    private static String[] segment(String rambling) {
        rambling = rambling.toLowerCase();
        return rambling.split(" ");
    }
}
