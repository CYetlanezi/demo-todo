# Overview

This is a demo application written in PHP and Java. It's not really meant to be
a great application, but is intended to show some example PHP and Java code, as
well as the benefits of building your applications using contemporary approaches
such as that provided by backbone.js (see
http://documentcloud.github.com/backbone/).

You can view the application here: http://demo-turksheadsw.dotcloud.com/

The front-end is taken from the backbone.js demo application "Todo List" by
Jérôme Gravel-Niquet. I've made a few minor modifications, most important to
remove the use of HTML5 LocalStorage and instead utilize a real database
back-end.

I built the application so that it could easily be deploying using the cloud
provider DotCloud, which provides a very simple mechanism for publishing
applications without having to setup a full environment. Obviously, for a
real-world-system, you would need to implement something like this yourself.

Using DotCloud for a simple application like this is Free, so you should be
able to deploy this yourself if you want to see it running or tweak it.

NB: I assume that you are running on OS X for simplicity and time constraints.
In addition, I'm assuming that you have properly installed XCode so that you
have all the typical UNIX tools (such as make).

# Getting things setup.

1.  Sign-up for a free DotCloud account here:

    https://www.dotcloud.com/accounts/register/

2.  Download the DotCloud CLI

    You can do that using the following command:

        $ sudo easy_install-2.6 pip && sudo pip install dotcloud

    NB: Their installation instructions simply say to use easy_install, but
    my local Python is setup to use Python 2.6, so I used the version-specific
    easy_install. You probably want to skip using sudo and use virtualenv
    with your own account instead.

3.  Verify that you can use the DotCloud CLI and provide it your API Key.

    The CLI tool will prompt you for your API key, which can be found here:
    https://www.dotcloud.com/settings/

    To verify it works, just run the following command:

        $ dotcloud list

    If it works, you'll be prompted for the API Key and see nothing else. You
    can run it a second time and verify it saved your API Key if you want.

5.  Clone the repo.

    If you already have git installed, it should go something like this:

        $ git clone git@github.com/turksheadsw/demo-php.git

6.  Deploy the application using the PHP back-end.

    From the root directory of the git repository, run the following commands:

        $ make push-php
        $ make db-reset
        $ make launch-php

    This may take a brief period of time while dotcloud allocates the resources
    to host the application and deploys the code. Once that is all done, your
    default browser should launch, pointing directly at your new instance of
    the application.

7.  Deploy the application using the Java back-end.

    From the root directory of the git repository, run the following commands:

        $ make push-java
        $ make db-reset
        $ make launch-java

    This will build the java-based back-end and deploy it in place of the PHP
    back-end. When the browser launches, you should see that the homepage of
    the app now says the back-end is Java.
