# Middletier
This is a development container specifically for the middletier portion of the Server Side Exercise. It provides linting, debugging and java compilation.
It is fomulated specifically for Visual Studio Code

## Installing and running

1. Open the middletier directory within vscode
2. Install the Remote - Containers extension from microsoft
3. Run the command Remote - Containers Rebuild and Reopen in Container
To verify open your favorite browser to http://localhost:8080/PS/api/ftr/transactions

## Running
Run the task `run`. This will clean, install and run the tomee application with the code. Simply update the files and the task will rebuild the application and server.

## Debugging
Run the task `debug` the task will wait for you to connect by launching the configuration `Debug (Attach) - Remote`. Simply update the files and the task will rebuild the application and server, you will need to reconnect the `Debug (Attach) - Remote` run.

## Running Tests
1. Click on the test tube
2. Click on any test's play button