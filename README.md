# MiniGameAPI
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/921ba65af2d740beaed9045b8bd9b7ad)](https://app.codacy.com/gh/SimonAtelier/MiniGameAPI?utm_source=github.com&utm_medium=referral&utm_content=SimonAtelier/MiniGameAPI&utm_campaign=Badge_Grade_Settings)

## Testable MC Plugins?
In most cases the only way to test a bukkit/spigot plugin is to run the server.
And for almost every minigame it ist much worse; you need some real players around to test the core gameplay mechanis.

Wouldn't it be nice to have a plugin you can write Unit Tests for?
Wouldn't life be easier with the ability to simulate game play and mock Player Objects without running the server?

I think it would!
So let's try to build indepenent Plugins with the principles of Clean Architecture...

## Goal
According to the principles of clean architecture the MC server is an 'IO-Device'. The goal is to implement an api that is independent as possible from any MC server.
At the end we should have an application that is testable without the server running. This project is mainly experimental and for practice purposes. Wanna find out how it is possible to apply CA to MC plugins. I tried this a few years ago by implementing a minigame based on CA. The plugin was not decoupled enough. With this project I would like to give it a second try and learn even more about CA. If you are interested in this topic or wanna colab, feel free to let me know. Cheers! 
