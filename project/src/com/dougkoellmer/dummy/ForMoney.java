package com.dougkoellmer.dummy;

import java.io.Writer;

public class ForMoney extends SoftwareIHaveWritten
{
    public void override writeProjects()
    {
        writeLink("Book of Heroes",       "<a class='nocode' href='https://itunes.apple.com/us/app/book-of-heroes/id455999097?mt=8' target='_blank'>https://itunes.apple.com/us/app/book-of-heroes</a>");
        writeDetails
        (
            "Also for Android <a class='nocode' href='https://play.google.com/store/apps/details?id=com.venan.boh&hl=en' target='_blank'>here</a>. This is about as intense as a mobile RPG gets. "       +
            "I was on a three-person team that developed the cross-mobile C++ frontend "   +
            "and cloud-hosted Java backend. The codebase started from nothing, went on "   +
            "to serve millions of monthly active users in under a year, and birthed a "    +
            "generic framework powering other titles as well."
        );
  //----------------------------------------------------------------------------------------------------------------\\

        writeLink("HTML5 Science Labs",              "<a class='nocode' href='http://eagreinteractive.com/samples' target='_blank'>http://eagreinteractive.com/samples</a>");
        writeDetails
        (
            "A series of interactive simulations meant for embedding as widgets in "       +
            "various eLearning and eReading platforms. These are samples of work I did "   +
            "to take static images from existing print textbooks and bring them to life."
        );
  //----------------------------------------------------------------------------------------------------------------\\

        writeLink("Inspect and Protect",                  "<a class='nocode' href='http://cp.johnsonsimcenter.com' target='_blank'>http://cp.johnsonsimcenter.com</a>");
        writeDetails
        (
            "A game-based training module funded by a $1.5 million grant from the "        +
            "Department of Defense to teach metal corrosion prevention strategies on "     +
            "large structures. I was the lead programmer and co-designer, responsible "    +
            "for (a) developing a framework into which non-programmers could plug art, "   +
            "sound, and XML assets dynamically, and (b) digesting dry subject matter and " +
            "turning it into fun interactive lessons. Also required learning the basics "  +
            "of corrosion theory and working with subject matter experts in the field."
        );
  //----------------------------------------------------------------------------------------------------------------\\

        writeLink("Touch Tone Hero",           "<a class='nocode' href='http://www.gametrailers.com/videos/lpbewe/touch-tone-hero' target='_blank'>http://www.gametrailers.com/videos/lpbewe</a>");
        writeDetails
        (
            "A fun twist on popular rhythm games where the player uses a touch-tone "      +
            "keypad with real dial tones to play popular melodies with accuracy-based "    +
            "scoring. I was co-designer and lone programmer. It was available on the App " +
            "Store until we were forced to remove it because all the downloads were "      +
            "crashing iTunes' servers."
        );
    }
}



/*writeLink("Grand Theft Algebra");
        writeDetails
        (
            "Sorry for some horrible programmer art. This is an alpha release of a serious game funded by a FIPSE grant. It " +
            "teaches basic math skills in the context of a top-down driving game simlar to Grand Theft Auto. I was the lead " +
            "programmer responsible for car physics, tile-based rendering, AI, a mission scripting API, asset pipeline, and " +
            "tool development, the latter enabling artists and designers to create and test game-ready assets without programmer " +
            "intervention. Unfortunately the grant-funding ceased unexpectedly before it could be brought to a polished level."
        );
        */
