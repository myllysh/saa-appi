# saa-appi

This repository contains client and service project that form a weather service. 

### Instructions:
1) Fetch code
2) Open service pom.file in IntelliJ Idea and compile. Run (only tested from ide).
3) Open client pom.file in IntelliJ Idea and compile. Run (only tested from ide).
4) Open a web browser and navigate to http://localhost:9090

### Supported features:
Weather service supports fetching current temperature now and tomorrow for a given location. It also supports some favorite locations (see all in the combobox, add and remove).

The actual weather data is fetched from Ilmatieteenlaitos www.fmi.fi

### Known and expected issues:
- Tests are missing. Or not missing, but not written. Only manually tested.
- Favorite locations accepts duplicates and is case-sensitive. 
- My magic tricks with datetime might break at some corner cases.
- Parsing the weather result is SO BAD! It's functional yes, but not the fastest and absolutely not the safest way to do it. Tried formatting the data from RestTemplate directly to WeatherResultItem with JAXB, but didn't get it working, remains can be seen in package com.failed.parsing. Then tried querying the result with XPath but didn't get that working either. Problem is with the namespaces somehow, but after one full day of googling I kind of gave up and went with the quick and dirty.
- I'm not completely happy with the REST json item that is returned to the client. As such it's just the minimal solution to show the minimal stuff, will only serve this specific UI and would need complete refactoring if new data would be needed.



### Shortly,
I know it's not the best solution ever, but I'm still proud of getting it working, considering my somewhat humble starting point on this field. :) It's been really interesting and challenging, and I'm thankful of getting this opportunity to learn. At this point however, I'm too exhausted and would want and need some feedback to continue developing this further. At least for now. If something major comes up, can fix it of course.
