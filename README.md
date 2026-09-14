# Quotex Signal Analyzer — APK-ready Android project

This is a build-ready Android Studio starter project for a signal-analysis dashboard.

## What is included
- Kotlin + Jetpack Compose UI
- Pair selector
- Timeframe selector
- CALL / PUT / WAIT result card
- Confidence display
- Candle countdown UI
- Indicator checklist
- Clean Android project structure

## Important
The included analysis function is a deterministic DEMO. It does not fetch live Quotex data,
does not log in to Quotex, and does not place trades. Replace the demo analyzer with a
proper market-data provider and a tested strategy before production.

## Build
Open this folder in Android Studio, allow Gradle sync, then:
Build > Generate App Bundles or APKs > Generate APKs.

For command-line builds on a machine with Gradle/Android SDK configured:
./gradlew assembleDebug

The debug APK will normally be under:
app/build/outputs/apk/debug/app-debug.apk
