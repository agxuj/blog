<h1 style="font-size: 2.5em;"> Android App 软件设计参考</h1>
 




Author:[Dianne Hackborn](https://plus.google.com/105051985738280261832)

Reference:[How should I design my Android application?](https://plus.google.com/+DianneHackborn/posts/FXCCYxepsDU) 


"How should I design my Android application? What kind of MVC pattern should I use? What should I use for an event bus?"

We often see questions from developers that are asking from the Android platform engineers about the kinds of design patterns and architectures they use in their apps. But the answer, maybe surprisingly, is we often don't have a strong opinion or really an opinion at all.

(Edit to clarify: when I write "we" here I am talking about the Android platform team. I am not speaking for all of Google or Android developer relations. There are lots of good suggestions and opinions inside and outside of Google about how to write apps, and I am not intending to dismiss those.)

Should you use MVC? Or MVP? Or MVVM? I have no idea. Heck, I only know about MVC from school and had to do a Google search to find other options to put here.

This may be surprising, because Android could feel like it has strong opinions on how apps should be written. With its Java language APIs and fairly high-level concepts, it can look like a typical application framework that is there to say how applications should be doing their work. But for the most part, it is not.

It is probably better to call the core Android APIs a "system framework." For the most part, the platform APIs we provide are there to define how an application interacts with the **operating system**; but for anything going on purely within the app, these APIs are often just not relevant.

That said, the Android APIs can often look different (or higher level) from what one typically expects in an operating system, which may easily lead to confusion about how they should be used.

For an example of this, let's consider how an operating system defines "how to run an app." In a classic system, this is basically the contract it has with an application about when it should run:

`````
int main(...) {
    // My app goes here!
}
`````

So the operating system starts the app, calls its main() function, and the app goes off and runs and does what it wants until it decides it is done. And clearly it is not saying anything here about what the app should be doing or how it should be designed within that main function -- it's a pretty pure blank slate.

In Android, however, we explicitly decided we were not going to have a main() function, because we needed to give the platform more control over how an app runs. In particular, we wanted to build a system where the user never needed to think about starting and stopping apps, but rather the system took care of this for them... so the system had to have some more information about what is going on inside of each app, and be able to launch apps in various well-defined ways whenever it is needed even if it currently isn't running.

To accomplish this, we decomposed the typical main entry point of an app into a few different types of interactions the system can have with it. And these are the **Activity, BroadcastReceiver, Service,** and **ContentProvider** APIs that Android developers quickly become familiar with.

These classes may look like they are telling you how the internals of your app should work, but they are not! In fact, they are all about how your app needs to interact with the system (and how the system can coordinate its interaction with other apps). As long as that interaction with the system happens, we don't really care what goes on inside of the app.

To illustrate, let's briefly look at these different APIs and what they really mean to the Android system.

## Activity

This is the entry into an application for interacting with the user. From the system's perspective, the key interactions it provides with the app are:

* Keep track of what the user currently cares about (what is on screen) to ensure the process hosting that is kept running.
* Know that previously used processes contain things the user may return to (stopped activities), and thus more highly prioritize keeping those processes around.
* Help the application deal with the situation where its process is killed so the user can return to activities with their previous state restored.
* Provide a way for applications to implement user flows between each other, coordinated by the system. (The most classic example here being share.)

What we don't care about:

Once we have gotten in to this entry-point to your UI, we really don't care how you organize the flow inside. Make it all one activity with manual changes to its views, use fragments (a convenience framework we provide) or some other framework, or split it into additional internal activities. Or do all three as needed. As long as you are following the high-level contact of activity (it launches in the proper state, and saves/restores in the current state), it doesn't matter to the system.

## BroadcastReceiver

This is a mechanism for the system to deliver events to the application that may be outside of a regular user flow. Most importantly, because this is another well-defined entry into the app, the system can deliver broadcasts to apps even if they aren't currently running. So, for example, an app can schedule an alarm to post a notification to tell the user about an upcoming event... and by delivering that alarm to a BroadcastReceiver of the app, there is no need for the app to remain running until the alarm goes off.

What we don't care about:

Dispatching events within an app is an entirely different thing. Whether you use some event bus framework, implement your own callback system, whatever... there is no reason to use the system's broadcasting mechanism, since you aren't dispatching events across apps. (In fact there is good reason not to -- there is a lot of unnecessary overhead and many potential security issues if using a global broadcast mechanism for the internal implementation of an app.) We do provide the **LocalBroadcastManager** convenience class that implements a purely in-process intent dispatching system with a similar API to the system's APIs, if you happen to like them. But again, there is no reason to use that over something else for things going on purely within your app.

## Service

A general-purpose entry point for keeping an app running in the background for all kinds of reasons. There are actually two very distinct semantics services tell the system about how to manage an app:

Started services are simply telling the system to, for some reason, "keep me running until I say I am done." This could be to sync some data in the background or play music even after the user leaves the app. Those also represent two different types of started services that modify how the system handles them:

* Music playback is something the user is directly aware of, so the app tells the system this by saying it wants to be foreground with a notification to tell the user about it; in this case the system knows that it should try really hard to keep that service's process running, because the user will be unhappy if it goes away.

* A regular background service is not something the user is directly aware as running, so the system has more freedom in managing its process. It may allow it to be killed (and then restarting the service sometime later) if it needs RAM for things that are of more immediate concern to the user.

Bound services are running because some other app (or the system) has said that it wants to make use of the service. This is basically the service providing an API to another process. The system thus knows there is a dependency between these processes, so if process A is bound to a service in process B, it knows that it needs to keep process B (and its service) running for A. Further, if process A is something the user cares about, than it also knows to treat process B as something the user also cares about.

Because of their flexibility (for better or worse), services have turned out to be a really useful building block for all kinds of higher-level system concepts. Live wallpapers, notification listeners, screen savers, input methods, accessibility services, and many other core system features are all built as services that applications implement and the system binds to when they should be running.

What we don't care about:

Android doesn't care about things going on within your app that don't have any impact on how it should manage your process, so there is no reason to use services in these cases. For example, if you want to start some background operation to download data for your UI, you should not use a service for this -- it is actually important to not be telling the system to keep your process running while doing this, because it really doesn't need to be and the system would be better off having more freedom in managing it with other things the user is doing.

If you just make a simple background thread (or whatever non-service mechanism you want) to do the downloading, you will get the semantics you want: while the user is in your UI, the system will keep your process running for that, so the download will never be interrupted. When they leave your UI, your process will still be kept around (cached) and able to continue downloading, as long as its RAM isn't needed elsewhere.

Likewise for connecting different parts of your app together, there is no reason to bind to a service that is running in the same process as the one binding to it. Doing so is not actively harmful -- the system just sees a dependency from the process to itself so doesn't try to keep it around any more than usual -- but it is a bunch of unnecessary work for both you and the system. Instead, you can just use singletons or other normal in-process patterns for connecting pieces of your app together.

## ContentProvider

Finally, the ContentProvider is a fairly specialized facility for publishing data from an app to other places. People generally think of them as an abstraction on a database, because there is a lot of API and support built in to them for that common case... but from the system design perspective, that isn't their point.

What these are to the system is an entry-point into an app for publishing named data items, identified by a URI scheme. Thus an app can decide how it wants to map the data it contains to a URI namespace, handing out those URIs to other entities which can in turn use them to access the data. There are a few particular things this allows the system to do in managing an app:

* Handing out a URI doesn't require the app remain running, so these can go all over the place with the owning app being dead. Only at the point where someone tells the system, "hey give me the data for this URI" does it need to make sure the app owning that data is running, so it can ask the app to retrieve and return the data.

* These URIs also provide an important fine-grained security model. For example, an application can place the URI for an image it has on the clipboard, but leave its content provider locked up so nobody can freely access it. When another app pulls that URI off the clipboard, the system can give it a temporary "URI permission grant" so that it is allowed to access the data only behind that URI, but nothing else in the app.

What we don't care about:

It doesn't really matter how you implement the data management behind a content provider; if you don't need structured data in a SQLite database, don't use SQLite. For example, the FileProvider helper class is an easy way to make raw files in your app available through a content provider.

Also, if you are not publishing data from your app for others to use, there is no need to use a content provider at all. It is true, because of the various helpers built around content providers, this can be an easy way to put data in a SQLite database and use it to populate UI elements like a ListView. But if any of this stuff makes what you are trying to do more difficult, then feel free to not use it and instead use a more appropriate data model for your app.

