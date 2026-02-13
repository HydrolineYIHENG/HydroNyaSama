# Spotless 代码格式化工具使用指南

## 简介

本项目已配置 **Spotless** 插件，用于自动格式化和统一 Java 代码风格。

## 可用命令

### 1. 检查代码格式（不修改文件）

```bash
# 检查所有模块
./gradlew spotlessCheck

# 检查特定模块
./gradlew :common:spotlessCheck
./gradlew :fabric-1.20.1:spotlessCheck
```

### 2. 自动格式化代码（修改文件）

```bash
# 格式化所有模块
./gradlew spotlessApply

# 格式化特定模块
./gradlew :common:spotlessApply
./gradlew :fabric-1.20.1:spotlessApply
```

### 3. 查看格式化差异

```bash
# 查看所有模块的格式化差异
./gradlew spotlessDiagnose
```

## 配置说明

Spotless 当前配置包括：

1. **导入顺序**：java -> javax -> org -> com -> 其他
2. **自动移除未使用的导入**
3. **自动添加许可证头**
4. **统一代码格式**（缩进、空格、换行等）

## 推荐工作流程

### 方式一：手动格式化

```bash
# 1. 开发完成后，格式化代码
./gradlew spotlessApply

# 2. 提交代码前，检查格式
./gradlew spotlessCheck
```

### 方式二：IDE 集成（推荐）

#### IntelliJ IDEA

1. 安装 **Spotless** 插件：
   - Settings → Plugins → 搜索 "Spotless"
   - 安装后重启 IDE

2. 配置自动格式化：
   - Settings → Tools → Spotless → 勾选 "Format on Save"
   - 或者使用快捷键：Ctrl + Alt + I（Windows/Linux）或 Cmd + Option + I（Mac）

3. 启用后，每次保存文件时自动格式化

#### VS Code

1. 安装 **Spotless** 扩展：
   - 搜索 "Spotless Formatter"
   - 安装后重载窗口

2. 配置自动格式化：
   - Settings → Editor: Format On Save → 勾选

## 许可证头模板

所有 Java 文件会自动添加以下许可证头：

```java
/*
 * HydroNyaSama - ${project.name}
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
```

## CI/CD 集成

在 GitHub Actions 中，可以添加代码格式检查：

```yaml
- name: Check code format
  run: ./gradlew spotlessCheck
```

如果代码格式不符合规范，构建将失败。

## 常见问题

### Q: 为什么我的代码被自动格式化了？
A: 这是 Spotless 的正常行为，它会统一整个项目的代码风格。

### Q: 如何自定义代码风格？
A: 编辑 `build.gradle.kts` 中的 Spotless 配置部分。

### Q: 如何跳过某些文件？
A: 在 Spotless 配置中使用 `target()` 或 `targetExclude()` 来指定包含或排除的文件。

### Q: 格式化后代码有问题怎么办？
A: 检查格式差异：`./gradlew spotlessDiagnose`，确认格式化是否正确。

## 参考资料

- [Spotless 官方文档](https://github.com/diffplug/spotless)
- [Spotless Java 格式化配置](https://github.com/diffplug/spotless/tree/main/plugin-gradle/src/main/java/com/diffplug/gradle/spotless)
