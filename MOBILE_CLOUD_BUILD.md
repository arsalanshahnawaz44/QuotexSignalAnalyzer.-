# Mobile-only APK build

This project includes a GitHub Actions workflow for cloud building.

## Steps from an Android phone

1. Create/sign in to a GitHub account.
2. Create a new repository.
3. Upload the contents of this project (not the outer ZIP folder) to the repository.
4. Open the repository's **Actions** tab.
5. Select **Build Android APK**.
6. Tap **Run workflow**.
7. When the workflow finishes, open the completed run.
8. Download the artifact named **SignalAnalyzer-debug-apk**.
9. Extract it and install `app-debug.apk` on your Android phone.

## Important

The current analyzer is a DEMO UI/logic and does not connect to Quotex,
does not log into a Quotex account, and does not place trades. A real
production signal system requires a permitted/reliable market-data source
and proper strategy validation.
