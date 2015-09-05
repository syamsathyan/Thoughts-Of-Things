Vuoto Commons Lib
===================
#### A set of commonly used highly optmized routines, classes and functions.

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

