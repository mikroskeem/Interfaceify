# Interfaceify

WIP utility to extract methods from class to separate interface

Shorter (hackish) version:
```java
cat Foo.java | grep -v "=" | egrep "^[[:blank:]]?(public|private|protected)?[[:blank:]](.+?)\(\) {" | \
    sed 's/public//; s/ {/;/; s/^[[:blank:]]*//; s/[[:blank:]]*$//'
```

_WTFPL_
