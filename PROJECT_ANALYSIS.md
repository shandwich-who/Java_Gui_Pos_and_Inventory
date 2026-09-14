# Java GUI POS and Inventory System - Project Analysis

## 📋 Project Overview

This is a **Point of Sale (POS) and Inventory Management System** built with **Java Swing** and **Microsoft Access Database**. The application provides a GUI interface for managing product inventory and processing sales transactions.

**Language**: Java  
**Framework**: Swing (GUI)  
**Database**: Microsoft Access (.accdb)  
**Created**: May 25, 2023

---

## 📁 Project Structure

```
Java_Gui_Pos_and_Inventory/
├── src/Package/
│   ├── Main.java              # Entry point
│   ├── Login.java             # Authentication
│   ├── Home.java              # Dashboard
│   ├── Pos.java               # POS System (1065 lines)
│   ├── Inventory.java         # Inventory Manager (900 lines)
│   └── AccessMs.java          # Database Connection
├── Database1.accdb            # Inventory Data
├── Database2.accdb            # Transaction Data
├── Project_system.iml         # IntelliJ Config
├── libs/                      # Dependencies
└── [icon files]               # UI Resources
    ├── home_icon.png
    ├── login_icon.png
    ├── pos.png
    ├── inventory.png
    ├── inventory_icon.png
    ├── payment_icon.png
    └── search_icon.png
```

---

## 📊 Module Overview

| Module | Lines | Purpose |
|--------|-------|---------|
| **Pos.java** | 1065 | Point of Sale transactions, payments, receipts |
| **Inventory.java** | 900 | Product management, stock tracking |
| **Login.java** | 109 | User authentication |
| **Home.java** | 71 | Navigation dashboard |
| **AccessMs.java** | 37 | Database connection manager |
| **Main.java** | 20 | Application entry point |

---

## 🐛 Critical Bugs Found (20 Total)

### 🔴 CRITICAL ISSUES (6)

#### 1. **Hardcoded Database Path**
- **File**: `AccessMs.java:19-20`
- **Severity**: CRITICAL
- **Issue**: Path hardcoded to specific user directory
```java
// BROKEN
connect_1 = DriverManager.getConnection(
    "jdbc:ucanaccess://C:/Users/Andrei Program/Documents/Project  Gui java/Project_system/Database1.accdb");
```
- **Impact**: Application only works on original developer's machine
- **Fix**: Use relative paths or config file

#### 2. **SQL Injection Vulnerability**
- **Files**: `Inventory.java:259-264, 278-283, 614-618, 636-640`
- **Severity**: CRITICAL
- **Issue**: String concatenation in SQL queries
```java
// VULNERABLE
PreparedStatement ps = connection.prepareStatement(
    "Update `table1` Set `Item Code` = ' " + itm_C + " ' ... Where `Item Code` = '" 
    + tableModel.getValueAt(table.getSelectedRow(), 0).toString() + "'");
```
- **Impact**: Attackers can modify/delete database records
- **Fix**: Use parameterized queries with `?` placeholders

#### 3. **Inverted Payment Logic**
- **File**: `Pos.java:641`
- **Severity**: CRITICAL
- **Issue**: Payment validation backwards
```java
// WRONG - Total=50, Payment=60 gets REJECTED
if (countingNumber >= amount) {  // Should be <
    JOptionPane.showMessageDialog(..., "Not Enough");
}
```
- **Impact**: Customers can't complete valid transactions
- **Fix**: Change `>=` to `<`

#### 4. **Frame Disposed Before Visibility**
- **File**: `Pos.java:503`
- **Severity**: CRITICAL
- **Issue**: Search window disposes before showing
```java
sh_Frame.dispose();              // ← WRONG (before visible)
sh_Frame.setLocationRelativeTo(null);
sh_Frame.setVisible(true);       // Frame already disposed!
```
- **Impact**: Search window never appears
- **Fix**: Remove dispose or move to window close event

#### 5. **Vector Duplication Loop**
- **Files**: `Pos.java:428`, `Inventory.java:841`
- **Severity**: CRITICAL
- **Issue**: Loop adds same row data multiple times
```java
// WRONG - if Loop=6, each row added 7 times (0-6)
while (resultSet.next()) {
    Vector<Object> vector = new Vector<>();
    for (int i = 0; i <= Loop; i++) {  // Should not loop here
        vector.add(resultSet.getString("Item Code"));
        vector.add(resultSet.getString("Item Name"));
        // ... adds same row repeatedly
    }
    tableModel.addRow(vector);
}
```
- **Impact**: Tables show duplicate/corrupted data
- **Fix**: Remove inner loop, add each column once

#### 6. **Hardcoded Credentials**
- **File**: `Login.java:39-44`
- **Severity**: CRITICAL
- **Issue**: Plain text password
```java
if (str_user.equals("admin")) {
    if (str_pass.equals("admin")) {  // Fixed password!
        // Accept login
    }
}
```
- **Impact**: No security; anyone can login
- **Fix**: Use database-stored hashed passwords

---

### 🟠 HIGH PRIORITY ISSUES (4)

#### 7. **Resource Connection Leaks**
- **Files**: Throughout (50+ instances)
- **Issue**: Database connections never closed
```java
// MEMORY LEAK
AccessMs accessMS = new AccessMs();
Connection conn = accessMS.getConnection2();
PreparedStatement ps = conn.prepareStatement(...);
ps.executeUpdate();
// Never close!
```
- **Impact**: Memory leaks, database locks
- **Fix**: Use try-with-resources or finally blocks

#### 8. **Null Pointer Exceptions**
- **File**: `Pos.java:465-475`
- **Issue**: No null checks before `.toString()`
```java
String column_Ic = sh_tableModel.getValueAt(sh_table.getSelectedRow(), 0).toString();
// NPE if null!
```
- **Impact**: App crashes on empty cells
- **Fix**: Check for null before toString()

#### 9. **Insufficient Input Validation**
- **File**: `Inventory.java:366-371`
- **Issue**: Only checks empty, not invalid values
```java
if (itemDescription_tf.getText().equals("")) {
    // No validation for:
    // - Negative prices
    // - Zero values
    // - Invalid formats
}
```
- **Impact**: Invalid data in database

#### 10. **KeyAdapter Logic Error**
- **File**: `Inventory.java:188-190`
- **Issue**: Wrong operator (OR instead of AND)
```java
// WRONG - prevents delete/backspace
if (!(Character.isDigit(number)) || number == KeyEvent.VK_DELETE || number == KeyEvent.VK_BACK_SPACE) {
    e.consume();  // Consuming delete/backspace!
}
// RIGHT - should be:
if (!(Character.isDigit(number) || number == KeyEvent.VK_DELETE || number == KeyEvent.VK_BACK_SPACE)) {
    e.consume();
}
```
- **Impact**: Users can't edit numeric fields properly

---

### 🟡 MEDIUM PRIORITY ISSUES (5)

#### 11. **Broad Exception Handling**
- **Multiple files**
- **Issue**: Catches all Exception types
```java
} catch (Exception e) {
    JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
}
```
- **Better**: Catch specific exceptions (SQLException, NumberFormatException)

#### 12. **Multiple JFrame Instantiation**
- **Multiple files**
- **Issue**: Creates new JFrame for each dialog
```java
// WASTEFUL
JOptionPane.showMessageDialog(new JFrame(), "Message");
// Creates JFrame just for message!
```
- **Better**: Reuse parent frame reference

#### 13. **No Database Close Methods**
- **File**: `AccessMs.java`
- **Issue**: No way to close connections
- **Better**: Add `closeConnections()` method

#### 14. **Missing Quantity Validation**
- **File**: `Pos.java:473-474`
- **Issue**: No checks for negative/excessive quantities
- **Better**: Validate against available stock

#### 15. **Repetitive Discount Code**
- **File**: `Pos.java:1015-1033`
- **Issue**: 4 identical if-else blocks
- **Better**: Use array/map lookup with loop

---

### 🔵 LOW PRIORITY ISSUES (5)

#### 16. **Poor Package Naming**
- Package named `Package` (too generic)
- Should be `com.pos.gui` or similar

#### 17. **Missing JavaDoc**
- No class/method documentation
- Makes maintenance difficult

#### 18. **Hardcoded Values**
- Magic numbers for coordinates: `setBounds(60, 50, 160, 30)`
- Hardcoded colors: `Color.decode("#2196F3")`
- Should be in constants file

#### 19. **No Design Pattern**
- All logic in GUI classes
- No separation of concerns
- No MVC architecture

#### 20. **Outdated Dependencies**
- UCanAccess driver for Microsoft Access (may be outdated)
- No explicit dependency management

---

## 📋 Code Examples - Bugs & Fixes

### Bug #2: SQL Injection (Inventory.java:614-618)

**VULNERABLE CODE:**
```java
PreparedStatement preparedStatement = connection
    .prepareStatement("Update `table1` Set `Item Code` = ' " + itm_C
        + " ' ,`Item Description` = ' " + ItmD + " ', Price = ' " + ItmP + " ', Size = ' "
        + ItmS + " ', Stocks = ' " + ItmStk + " ',`Re-Order Point` = ' " + Rop
        + " ' , Remarks = '" + re_marks1 + "' Where `Item Code` = '"
        + tableModel.getValueAt(table.getSelectedRow(), 0).toString() + "' ");
int affect = preparedStatement.executeUpdate();
```

**SECURE FIX:**
```java
String sql = "Update `table1` Set `Item Code` = ?, `Item Description` = ?, Price = ?, "
    + "Size = ?, Stocks = ?, `Re-Order Point` = ?, Remarks = ? Where `Item Code` = ?";
PreparedStatement preparedStatement = connection.prepareStatement(sql);

preparedStatement.setString(1, itm_C);
preparedStatement.setString(2, ItmD);
preparedStatement.setLong(3, ItmP);
preparedStatement.setString(4, ItmS.toString());
preparedStatement.setLong(5, ItmStk);
preparedStatement.setLong(6, Rop);
preparedStatement.setString(7, re_marks1);
preparedStatement.setString(8, tableModel.getValueAt(table.getSelectedRow(), 0).toString());

int affect = preparedStatement.executeUpdate();
```

---

### Bug #5: Vector Loop (Pos.java:428)

**INCORRECT CODE:**
```java
while (rsltSet.next()) {
    Vector<Object> sh_vector = new Vector<>();
    for (int i = 0; i <= Loop; i++) {  // Loop=6 means 7 iterations!
        sh_vector.add(rsltSet.getString("Item Code"));
        sh_vector.add(rsltSet.getString("Item Name"));
        sh_vector.add(rsltSet.getString("Price"));
        sh_vector.add(rsltSet.getString("Size"));
        sh_vector.add(rsltSet.getString("Stocks"));
        sh_vector.add(rsltSet.getString("Re-Order Point"));
        sh_vector.add(rsltSet.getString("Remarks"));
    }
    sh_tableModel.addRow(sh_vector);  // Row with 42 values instead of 7!
}
```

**CORRECT CODE:**
```java
while (rsltSet.next()) {
    Vector<Object> sh_vector = new Vector<>();
    sh_vector.add(rsltSet.getString("Item Code"));
    sh_vector.add(rsltSet.getString("Item Description"));
    sh_vector.add(rsltSet.getString("Price"));
    sh_vector.add(rsltSet.getString("Size"));
    sh_vector.add(rsltSet.getString("Stocks"));
    sh_vector.add(rsltSet.getString("Re-Order Point"));
    sh_vector.add(rsltSet.getString("Remarks"));
    sh_tableModel.addRow(sh_vector);  // One row per DB record
}
```

---

### Bug #7: Connection Leak (Multiple Files)

**MEMORY LEAK:**
```java
AccessMs accessMS = new AccessMs();
Connection connected = accessMS.getConnection2();
PreparedStatement prstStatement = connected.prepareStatement(...);
prstStatement.executeUpdate();
// Connections persist forever!
```

**FIX - Try-With-Resources:**
```java
try (AccessMs accessMS = new AccessMs();
     Connection connected = accessMS.getConnection2();
     PreparedStatement prstStatement = connected.prepareStatement(...)) {
    prstStatement.executeUpdate();
} catch (SQLException e) {
    logger.error("Database error", e);
}
// Auto-closes all resources
```

**FIX - With Finally:**
```java
AccessMs accessMS = new AccessMs();
Connection connected = accessMS.getConnection2();
try {
    PreparedStatement prstStatement = connected.prepareStatement(...);
    prstStatement.executeUpdate();
} finally {
    try {
        prstStatement.close();
        connected.close();
    } catch (SQLException e) {
        logger.error("Error closing resources", e);
    }
}
```

---

## 🔧 Priority Action Items

### 🚨 CRITICAL (Fix Immediately - Blocks Production)
- [ ] Fix SQL injection in all UPDATE/DELETE queries
- [ ] Fix payment logic inversion (Bug #3)
- [ ] Remove frame.dispose() before setVisible (Bug #4)
- [ ] Fix vector duplication loop (Bug #5)
- [ ] Externalize hardcoded database path

### ⚠️ HIGH (Fix This Sprint)
- [ ] Add connection closing in all DB operations
- [ ] Add null checks before toString()
- [ ] Add input validation (ranges, formats)
- [ ] Fix KeyAdapter logic
- [ ] Add try-with-resources for resources

### 💡 MEDIUM (Fix Next Sprint)
- [ ] Replace broad Exception catches
- [ ] Reuse JFrame references
- [ ] Add closeConnections() to AccessMs
- [ ] Implement quantity stock validation
- [ ] Refactor discount code

### 📝 LOW (Refactor)
- [ ] Rename package to meaningful name
- [ ] Add JavaDoc comments
- [ ] Extract magic numbers to constants
- [ ] Implement MVC pattern
- [ ] Add unit tests

---

## 📈 Summary

| Category | Count |
|----------|-------|
| **Critical Bugs** | 6 |
| **High Priority** | 4 |
| **Medium Priority** | 5 |
| **Low Priority** | 5 |
| **Total Issues** | **20** |

---

## ✅ Recommendations

1. **Security**: Replace all string concatenation SQL with parameterized queries
2. **Stability**: Add proper exception handling and resource management
3. **Usability**: Fix logic bugs (payment, frame disposal, loops)
4. **Performance**: Close database connections properly
5. **Maintainability**: Refactor to MVC pattern, add tests

---

*Analysis Date: September 14, 2026*  
*Repository: shandwich-who/Java_Gui_Pos_and_Inventory*  
*Commit: e757b71875794d71d803ef0c81dbdafe4371fb39*
