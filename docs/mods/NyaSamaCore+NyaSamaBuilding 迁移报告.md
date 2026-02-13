# NyaSamaCore + NyaSamaBuilding 迁移报告

## 背景
- 迁移源：`.reference/NyaSamaCore`、`.reference/NyaSamaBuilding`（Forge 1.12.2）。
- 迁移目标：当前仓库（多模块 + 多版本矩阵），需要同时支持 1.16.5 / 1.18.2 / 1.20.1 的 Forge/Fabric；共享逻辑集中在 `common/`，各 Loader 仅保留薄适配层。
- 迁移后的包名前缀要求：`cn.hydcraft.hydronyasama.core.*`、`cn.hydcraft.hydronyasama.building.*`。

## 已成功迁移（可编译、可注册）
### 1) “共享声明 + 平台注册”方案（跨 Forge/Fabric、跨版本）
- common 声明层（不依赖 `net.minecraft.*`）：`ContentId`、`ContentRegistrar`、`ModContent`。
- 平台实现层（依赖各版本 MC/Forge/Fabric API）：在每个 `forge-*`/`fabric-*` 子工程实现 `*ContentRegistrar`，并在入口调用 `ModContent.bootstrap(...)`。

### 2) 已接入并注册的最小示例内容
- building（立方体方块 + BlockItem）
  - `hydronyasama:anti_static_floor_block`
  - `hydronyasama:asphalt_block`
  - `hydronyasama:black_marble_block`
- core（装饰方块 + BlockItem）
  - `hydronyasama:nsdn_logo`
  - `hydronyasama:nst_sign`

### 3) 已迁移的最小资源集（可被所有版本复用）
- `assets/hydronyasama/blockstates/*`
- `assets/hydronyasama/models/block/*`
- `assets/hydronyasama/models/item/*`
- `assets/hydronyasama/lang/en_us.json`、`zh_cn.json`

## 迁移后仍不稳定/需要继续迁移的模块（有方案，但尚未完成）
### 1) NyaSamaBuilding 的大规模方块体系
- 旧版定义包含大量派生形态：Carpet/Edge/Railing/Roof/Fence/FenceGate/Pane/Slab/Stairs/Strip/VSlab/VStrip/Wall 等。
- 在 1.16.5/1.18.2/1.20.1 间，方块属性、BlockState、注册入口、以及部分几何/连接逻辑存在显著差异；建议继续沿用“common 仅声明 spec，版本侧 factories 负责生成 Block 实例”的路径推进。

### 2) 创造模式分组（Creative Tab / Item Group）
- 1.16.5/1.18.2/1.20.1 的 Forge/Fabric 创造标签 API 差异较大，需要做版本侧实现并在 common 侧仅保留声明与 id。

### 3) 资源与数据生成（Textures/Tags/Loot/Recipes）
- 当前仅迁移了 JSON 结构与语言文件；PNG 纹理尚未纳入。
- 随着方块数量变大，推荐后续引入 Datagen 统一生成 blockstate/model/itemmodel/tag/loot/recipe，以降低维护成本。

## 当前无法迁移/暂缓迁移的模块（跨版本代价大，且目标工程缺少对应基础设施）
### 1) NSASM 编程系统 + NGT 终端
- 旧版实现依赖 1.12 的 GUI、书本编辑、以及一整套网络交互与渲染逻辑；在 1.16+ 需要重写 GUI/Screen、网络、以及输入交互。

### 2) 列车控制系统（NTP-8Bit/NTP-32Bit + 物理引擎）
- 旧版强依赖 1.12 的实体/事件/按键与网络同步结构；跨到 1.16+ 需要重做事件绑定与同步协议。

### 3) 通信系统（各类 TileEntity/BlockEntity + 网络）
- 旧版 TileEntity 与能力系统/数据同步方式，与 1.16+ BlockEntity、DataFixer/同步范式差异较大，且当前目标仓库还未建立“方块实体 + 菜单 + 同步”的基础设施框架。

## 后续推进建议（下一阶段）
1. 扩展 `BuildingContent`：把旧版 `BlockLists.HARD/SOFT` 全量转为 spec，并逐步补齐各派生形态的版本侧 factories。
2. 引入创造标签：common 声明 tab id，版本侧实现注册与图标。
3. 引入 Datagen：优先生成 cube 系列与其 item model，再逐步覆盖派生形态的 blockstate。
4. 再评估 Core 的“NSASM/列车/通信”是否要进本仓库；若要迁移，建议先在 common 建立网络/BlockEntity/Screen 的跨平台骨架。

