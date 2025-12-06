# JSF POC Application with PrimeFaces and AG Grid

A sample JavaServer Faces (JSF) application demonstrating the use of PrimeFaces UI components and AG Grid for data display.

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- **Java JDK 11** or higher
- **Apache Maven 3.6+**
- **Git** (for cloning the repository)
- A modern web browser (Chrome, Firefox, Edge)

### Verify Installation

**Windows (PowerShell/CMD):**
```powershell
java -version
mvn -version
git --version
```

**Linux/macOS:**
```bash
java -version
mvn -version
git --version
```

### Environment Variables (if not set)

Ensure `JAVA_HOME` is set to your JDK installation path:

**Windows:**
```powershell
# Check if JAVA_HOME is set
echo $env:JAVA_HOME

# If not set, add to system environment variables or run:
$env:JAVA_HOME = "C:\Program Files\Java\jdk-11"
```

**Linux/macOS:**
```bash
# Check if JAVA_HOME is set
echo $JAVA_HOME

# If not set, add to ~/.bashrc or ~/.zshrc:
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk
```

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/abhishekbande/jsf-primefaces-aggrid-poc.git
cd jsf-primefaces-aggrid-poc
```

Or if you have the project as a ZIP file, extract it and navigate to the folder:

**Windows:**
```powershell
cd C:\path\to\jsf-primefaces-aggrid-poc
```

**Linux/macOS:**
```bash
cd /path/to/jsf-primefaces-aggrid-poc
```

### 2. Build the Application

```bash
mvn clean install
```

Expected output should end with:
```
[INFO] BUILD SUCCESS
```

### 3. Run the Application

```bash
mvn jetty:run
```

Wait until you see:
```
[INFO] Started Jetty Server
```

### 4. Access the Application

Open your web browser and navigate to:

```
http://localhost:9090/index.xhtml
```

## 🛑 Stopping the Application

Press `Ctrl + C` in the terminal where the application is running.

## 📁 Project Structure

```
jsf-primefaces-aggrid-poc/
├── pom.xml                                    # Maven configuration
├── README.md                                  # This file
└── src/
    └── main/
        ├── java/
        │   └── com/poc/jsf/
        │       ├── bean/
        │       │   └── SegmentationBean.java  # Main backing bean
        │       └── model/
        │           ├── Field.java             # Field model
        │           ├── Segment.java           # Segment model
        │           └── LoanData.java          # Loan data model
        └── webapp/
            ├── index.xhtml                    # Main page
            ├── resources/
            │   └── css/
            │       └── style.css              # Application styles
            └── WEB-INF/
                ├── web.xml                    # Web configuration
                ├── faces-config.xml           # JSF configuration
                └── beans.xml                  # CDI configuration
```

## ✨ Features

### Left Panel - Add to Selection
- **Fields Tab**: List of available fields to select
- **Segments Tab**: View segments
- **Search**: Filter fields by name
- **Click to Select**: Click any field to select it

### Middle Panel - Selected Segments
- **Add Segment**: Click the `+` button to add a new segment
- **Delete Segment**: Click the `×` on segment header to remove it
- **Add Field to Segment**: Select a field, then click `+` in the segment
- **Remove Field**: Click the `−` next to any field
- **Expand/Collapse**: Click segment header to toggle
- **Clear All**: Remove all fields from all segments

### Right Panel - Report Preview
- **AG Grid Tables**: Display loan data in interactive grids
- **Toggle Options**: Include Empty Segments, Auto Refresh
- **Download**: Export report (placeholder)

### Toolbar Functions
- **Copy**: Copy segment (placeholder)
- **Move**: Move segment (placeholder)
- **Duplicate**: Duplicate first segment
- **Delete**: Shows tip for deleting segments
- **Collapse All**: Collapse all segments

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 11+ | Runtime |
| JSF (Mojarra) | 2.3.21 | Web Framework |
| PrimeFaces | 12.0.0 | UI Components |
| AG Grid | 30.2.0 | Data Grid |
| CDI (Weld) | 3.1.9 | Dependency Injection |
| Jetty | 9.4.53 | Embedded Server |
| Gson | 2.10.1 | JSON Processing |

## 🔧 Configuration

### Change Port (Default: 9090)

Edit `pom.xml` and modify the Jetty plugin configuration:

```xml
<httpConnector>
    <port>9090</port>  <!-- Change this -->
</httpConnector>
```

### Change Theme

Edit `src/main/webapp/WEB-INF/web.xml`:

```xml
<context-param>
    <param-name>primefaces.THEME</param-name>
    <param-value>saga</param-value>  <!-- Change theme name -->
</context-param>
```

## ❗ Troubleshooting

### Port Already in Use

If you get "Address already in use" error:

**Windows (PowerShell):**
```powershell
# Find process using port 9090
netstat -ano | findstr :9090

# Kill the process (replace <PID> with actual PID from above)
taskkill /PID <PID> /F
```

**Linux/macOS:**
```bash
# Find and kill process using port 9090
lsof -i :9090
kill -9 <PID>

# Or use fuser
fuser -k 9090/tcp
```

### View Expired Exception

If you see "View could not be restored" error:
- Simply refresh the page (F5 or Ctrl+R)
- This happens when the server restarts

### Build Failures

```bash
# Clean and rebuild with updated dependencies
mvn clean install -U

# Skip tests if needed
mvn clean install -DskipTests
```

### Maven Not Found

Ensure Maven is installed and added to your PATH:

**Windows:**
1. Download Maven from https://maven.apache.org/download.cgi
2. Extract to `C:\Program Files\Apache\maven`
3. Add `C:\Program Files\Apache\maven\bin` to your PATH

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install maven
```

**macOS (Homebrew):**
```bash
brew install maven
```

### Java Version Issues

This application requires Java 11 or higher. If you have multiple Java versions:

**Windows:**
```powershell
# Check Java version
java -version

# Set JAVA_HOME to JDK 11+
$env:JAVA_HOME = "C:\Program Files\Java\jdk-11"
```

**Linux/macOS:**
```bash
# List available Java versions
update-alternatives --list java  # Linux
/usr/libexec/java_home -V        # macOS

# Set JAVA_HOME
export JAVA_HOME=/path/to/jdk-11
```

## 🔄 Quick Start Commands

For convenience, here are all commands in sequence:

```bash
# 1. Clone (if using git)
git clone https://github.com/abhishekbande/jsf-primefaces-aggrid-poc.git
cd jsf-primefaces-aggrid-poc

# 2. Build
mvn clean install

# 3. Run
mvn jetty:run

# 4. Open browser to: http://localhost:9090/index.xhtml
```

## 📝 License

This is a Proof of Concept (POC) application for demonstration purposes.

## 👤 Author

POC JSF Application - Segmentation Demo
