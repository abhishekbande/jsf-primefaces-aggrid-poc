# JSF POC Application with PrimeFaces and AG Grid

A sample JavaServer Faces (JSF) application demonstrating the use of PrimeFaces UI components and AG Grid for data display.

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- **Java JDK 11** or higher
- **Apache Maven 3.6+**
- A modern web browser (Chrome, Firefox, Edge)

### Verify Installation

```bash
# Check Java version
java -version

# Check Maven version
mvn -version
```

## 🚀 Getting Started

### 1. Clone or Download the Project

```bash
cd C:\Repos\POC JSF
```

### 2. Build the Application

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn jetty:run
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
POC JSF/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/poc/jsf/
│       │       ├── bean/
│       │       │   └── SegmentationBean.java    # Main backing bean
│       │       └── model/
│       │           ├── Field.java               # Field model
│       │           ├── Segment.java             # Segment model
│       │           └── LoanData.java            # Loan data model
│       └── webapp/
│           ├── WEB-INF/
│           │   ├── web.xml                      # Web configuration
│           │   ├── faces-config.xml             # JSF configuration
│           │   └── beans.xml                    # CDI configuration
│           ├── resources/
│           │   └── css/
│           │       └── style.css                # Application styles
│           └── index.xhtml                      # Main page
└── pom.xml                                      # Maven configuration
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

```bash
# Windows - Find and kill process on port 9090
netstat -ano | findstr :9090
taskkill /PID <PID> /F

# Or change the port in pom.xml
```

### View Expired Exception

If you see "View could not be restored" error:
- Simply refresh the page (F5 or Ctrl+R)
- This happens when the server restarts

### Build Failures

```bash
# Clean and rebuild
mvn clean install -U

# Skip tests if needed
mvn clean install -DskipTests
```

## 📝 License

This is a Proof of Concept (POC) application for demonstration purposes.

## 👤 Author

POC JSF Application - Segmentation Demo

