# Feature Delivery

A sample application POC to test the feasibility
of [Jetpack Compose](https://developer.android.com/jetpack/compose)
+ [Play Feature Delivery](https://developer.android.com/guide/playcore/feature-delivery).
This app shows 2 ways for feature delivery; via Activity
or [Compose NavGraph](https://developer.android.com/jetpack/compose/navigation)

## Activity

```
context.startActivity(Intent(Intent.ACTION_VIEW).apply {
  data = Uri.parse("app://${appRoute.packageName}")
  `package` = context.packageName
  })
```

## NavGraph

```
navController.graph.addDestination(
    ComposeNavigator.Destination(
        provider[ComposeNavigator::class],
        (Class.forName(route.navigationRoute).kotlin.createInstance() as FeatureNavGraph)
            .moduleScreenComposable(
                modifier = modifier,
                navController = appState.navController
            )
       ).apply {
        this.route = route.route
        }
    )
```

## 📃 License

```
Copyright 2022 FrestoInc

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
