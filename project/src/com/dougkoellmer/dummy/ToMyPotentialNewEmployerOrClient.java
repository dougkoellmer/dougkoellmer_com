package com.dougkoellmer.dummy;

import java.io.Writer;

public class ToMyPotentialNewEmployerOrClient extends Introduction
{
    public void override writeIntro(Document out)
    {
        out.write("Hi, I'm Doug! I have...\n");

        for( int i = 0; i < m_achievements.length; i++ )
            out.write(m_achievements[i] + "\n");
    }

    private String[] m_achievements =
    {
        " - twelve years of professional software development experience.",
        " - two open source projects with over 100,000 lines of code combined (not counting comments!).",
        " - bootstrapped an EdTech startup, and almost broke even.",
        " - worked on all aspects of a successful mobile social MMORPG.",
        " - developed grant-funded training simulations for just about every 'Department' the U.S. government has.",
        " - written algorithms for rendering symbolicated, 2D-projected, hidden-line-removed schematics from 3D " +
        "     parametric surface geometry in architectural CAD projects.",
        " - never been able to explain to others what the above point means.",
        " - never figured out how to write clean PHP, but am always that guy that is forced to anyway.",
        " - used every language, design pattern, tool, and library that a business guy has ever heard of.",
        " - also used a few that would get me fired from most jobs."
    };
}