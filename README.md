FlyWheel - IOT
===================
#### A set of commonly used highly optimized routines, classes and functions.
> Born out of our experiment's in IOT  
> Mostly old school libs transitioning to IOT and DataScience labs  
> Assists with ~sense:feed:sample:log~ flow 

+ **FastFixedPump** - Fast Collection with ability to pump out (evict) fixed number of elements per access, threads safe and implemented for an IOT application where sensory data eviction required at periodic intervals as fixed parts
+ **CyclicFixedPump** - Fast Collection with ability to pump (cycle) without emptying the contents
+ **shuffle.Sattolo** - Sattolo shuffling
+ **ByteUtils** - General purpose utilities,Byte ~ Hex fast conversions etc.
+ **Math** - General purpose Math functions for Geo roundups, Ordinals
+ **HashUtils** - Hashing utils for conflict avoidance and consistent key building
+ **Strings** - String manipulation that uses FixedFastStringBuilder, utilizes jvm string pool and provides fast light methods.
+ **DateUtils** - Joda utilities
+ **http.HttpHelper** - Old School fast connectors (no external http client dependency, tested only in server VM's)
+ **FixedFastStringBuilder** - Non nonsense String builder with UTF-8 and append routines with lengh check excluded.
+ **io.LocalFileHelper** - General purpose local file creation and stream sinking utils
+ **io.StreamHelper** - IO Stream helper methods.
+ **command.SystemCommandExecutor** - Command executor with process support, interrupt handling, streaming io etc
+ **collection.FixedFastSet** - Set implementation with no growth / resize factors, you initialize a size and it stays, can be reset and reused, offers blazing speeds since hash is not used for object storage and retrieval, see below speed tests comparing with HashSet
+ **collection.FixedFastList** - List implementation with no growth / resize factors, you initialize a size and it stays, can be reset and reused, offers blazing speeds since hash is not used for object storage and retrieval, see below speed tests comparing with ArrayList

Tests
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

FixedFastList
------------
###### Sequential Add (10000000 items) - Comparing with ArrayList ######
    1. Time Taken by FastFixedList(s):2.217606438
    2. Time Taken by ArrayList(s):3.007124955
    ArrayList took :0.7895185170000003 seconds more than FixedFastList
    
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