Thoughts-Of-Things (ToT Commons)
===================
#### A set of commonly used highly optimized routines, classes and functions.
> Born out of our experiment's in Secure-IOT  
> Mostly old school libs rewritten for Safe-IOT and DataScience labs with a prioritization on lightweight alternatives

1. **FastFixedPump** - Fast Collection with ability to pump out (evict) fixed number of elements per access, threads safe and implemented for an IOT application where sensory data eviction required at periodic intervals as fixed parts
2. **CyclicFixedPump** - Fast Collection with ability to pump (cycle) without emptying the contents
3. **shuffle.Sattolo** - Sattolo shuffling
4. **ByteUtils** - General purpose utilities,Byte ~ Hex fast conversions etc.
5. **Math** - General purpose Math functions for Geo roundups, Ordinals
6. **StringUtils** - Fast and light utility methods.
7. **DateUtils** - Date/Time utilities
8. **HttpHelper** - Old School fast connectors (no external http client dependency, tested only in server VM's)
9. **FixedFastStringBuilder** - Non nonsense String builder with UTF-8 and append routines with lengh check excluded.
10. **LocalFileHelper** - General purpose local file creation and stream sinking utils
11. **StreamHelper** - IO Stream helper methods.
12. **SystemCommandExecutor** - Command executor with process support, interrupt handling, streaming io etc
13. **FixedFastSet** - Set implementation with no growth / resize factors, you initialize a size and it stays, can be reset and reused, offers blazing speeds since hash is not used for object storage and retrieval, see below speed tests comparing with HashSet
14. **FastFixedList** - List implementation with no growth / resize factors, you initialize a size and it stays, can be reset and reused, offers blazing speeds since hash is not used for object storage and retrieval, see below speed tests comparing with ArrayList
15. **WeakConcurrentHashMap** - Map Solution which stores entries only for a specific amount of time, and then expires after that time
16. **LRUHashMap** - Concurrent Map implementation with LeastRecentlyUsed algorithm based eviction to make room for items beyond the initial size

Test Results
=====
FixedFastSet
------------
###### Sequential Add (100000 items) - Comparing with HashSet ######
    1. Time Taken by FastFixedSet(s):0.011682581
    2. Time Taken by HashSet(s):0.032001233
    HashSet took :0.020318652 seconds more than FixedFastSet
###### Random Add (100000 items) - Compare with HashSet #######
    1. Time Taken by FastFixedSet(s):0.007161369
    2. Time Taken by HashSet(s):0.010048395
    HashSet took :0.002887026 seconds more than FixedFastSet

FastFixedList
------------
###### Sequential Add (10000000 items) - Comparing with ArrayList ######
    1. Time Taken by FastFixedList(s):2.217606438
    2. Time Taken by ArrayList(s):3.007124955
    ArrayList took :0.7895185170000003 seconds more than FixedFastList
    
###### Sequential Add (100000000 items) - Comparing with ArrayList ######
    1. Time Taken by FastFixedList(s):2.669169074
    2. Time Taken by ArrayList(s):Unknown due to OOM
    
Sattolo Shuffle
------------
###### Shuffle loop => Seed = 'SOYA'
    String[] a = {"S", "O", "Y", "A"};  
    String[] originalBackup = {"S", "O", "Y", "A"};  
    Sattolo.cycle(a);  
    while(!Arrays.deepEquals(originalBackup, a)) {
     System.out.println(Arrays.toString(a));  
     Sattolo.cycle(a);  
    }  
###### Shuffle loop result
[Y, A, O, S]  
[O, S, A, Y]   
[S, Y, O, A]  
[A, O, S, Y]  
[Y, A, O, S]  
[S, Y, A, O]  
[O, A, S, Y]  
[Y, S, O, A]  
[S, O, A, Y]  
[A, S, Y, O]  
[Y, O, S, A]  
[A, Y, O, S]  
[Y, O, S, A]  
[A, S, Y, O]  
[S, O, A, Y]  
[Y, A, S, O]  
[O, S, Y, A]  
[S, Y, A, O]  
[O, S, Y, A]  
[Y, A, S, O]  
[S, O, A, Y]  
[A, Y, O, S]  
[O, S, Y, A]  
[A, Y, O, S]  
[O, S, Y, A]  
[A, O, S, Y]  
[O, S, Y, A]  
[Y, O, A, S]  
[A, Y, S, O]  
[O, A, Y, S]  
[S, O, A, Y]  
[Y, A, S, O]  
[S, O, A, Y]  
[Y, A, S, O]  
[O, Y, A, S]  
[Y, S, O, A]  
[S, A, Y, O]   
[O, S, A, Y]  
[Y, A, O, S]   
[O, Y, S, A]  
[Y, S, A, O]  
[O, A, Y, S]  
[Y, O, S, A]  
[A, Y, O, S]  
[O, S, Y, A]  
[S, A, O, Y]  
[A, Y, S, O]  
[Y, S, O, A]  
[S, O, A, Y]  
[A, S, Y, O]  
[Y, O, S, A]  
[O, A, Y, S]  
[S, O, A, Y]  
[Y, S, O, A]  
[S, O, A, Y]  
[O, Y, S, A]  
[Y, A, O, S]  
[O, S, A, Y]  
[A, Y, S, O]  
[Y, S, O, A]  
[S, A, Y, O]  
[A, Y, O, S]  
[Y, S, A, O]  
