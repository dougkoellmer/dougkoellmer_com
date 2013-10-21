package com.dougkoellmer.dummy;

import java.io.Writer;

public class OpenSource extends SoftwareIHaveWritten
{
    public void override writeProjects()
    {
        writeLink("b33hive",                                      "<a class='nocode' href='http://www.b33hive.net' target='_blank'>http://www.b33hive.net</a>");
        writeDetails
        (
            "A massive, shared landscape of user-created, sandboxed HTML5 applications. "  +
            "b33hive exposes an API to userland and allows applications to communicate "   +
            "with each other. The core of this project is <a class='nocode' href='https://github.com/dougkoellmer/swarm' target='_blank'>swarm</a>, a next of class content " +
            "browser for the web."
        );
  //----------------------------------------------------------------------------------------------------------------\\

        writeLink("quickb2",                         "<a class='nocode' href='http://quickb2.dougkoellmer.com/bin/qb2DemoReel.swf' target='_blank'>http://quickb2.dougkoellmer.com/bin</a>");
        writeDetails
        (
            "Originally started as a convenience wrapper around Box2D, this has turned "   +
            "into an advanced physics engine with an event-driven DOM-style API, "         +
            "CSS-like property system, and so many other cool features that you'd get "    +
            "bored with me listing them. Source code is <a class='nocode' href='https://code.google.com/p/quickb2/' target='_blank'>here</a>."
        );
    }
}