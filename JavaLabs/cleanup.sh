#!/bin/sh
for m in `find . -iname '*.class'`; do rm -f $m; done
