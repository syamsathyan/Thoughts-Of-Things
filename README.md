FlyWheel - IOT
===================
#### A set of commonly used highly optimized routines, classes and functions.
> Born out of our experiment's in IOT  
> Mostly olds chool libs transitioning to IOT and DataScience labs  
> Assists with ~sense:feed:sample:log~ flow 

+ **Strings** - String manipulation that uses FixedFastStringBuilder, utilizes jvm string pool and provides fast light methods.
+ **DateUtils** - Joda utilities
+ **http.HttpHelper** - Old School fast connectors (no external http client dependency, tested only in server VM's)
+ **FixedFastStringBuilder** - Non nonsense String builder with UTF-8 and append routines with lengh check excluded.
+ **VMath** - General purpose Math functions for Geo roundups, Byte ~ Hex fast conversions etc.
+ **ByteUtils** - General purpose utilities.
+ **io.LocalFileHelper** - General purpose local file creation and stream sinking utils
+ **io.StreamHelper** - IO Stream helper methods.
+ **command.SystemCommandExecutor** - Command executor with process support, interrupt handling, streaming io etc
+ **collection.FixedFastSet** - Set implementation with no growth / resize factors, you initialize a size and it stays, can be reset and reused, offers blazing speeds since hash is not used for object storage and retrieval, see below speed tests comparing with HashSet
+ **collection.FixedFastList** - List implementation with no growth / resize factors, you initialize a size and it stays, can be reset and reused, offers blazing speeds since hash is not used for object storage and retrieval, see below speed tests comparing with ArrayList
+ **HashUtils** - Hashing utils for conflict avoidance and consistent key building
+ **FastFixedPump** - Fast Collection with ability to pump out (evict) fixed number of elements per access, threads safe and implemented for an IOT application where sensory data eviction required at periodic intervals as fixed parts
+ **shuffle.Sattolo** - Sattolo shuffling

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




