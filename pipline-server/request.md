# 流水线管理

## 一、	概述
流水线的本质是研发-交付的流程，它把流程中的不同阶段和任务串接在一起，并且（可以设置为）自动化地一步一步地执行。简单的例子，手工触发，构建并部署到一个特定的环境，是一条基本的流水线。复杂的例子，源代码提交自动触发，通过各个环节和阶段的构建、部署、各种检测工作，直到上线，是一条完整的端到端的流水线。
流水线的存在不同类型的流程任务: 
**代码检出、构建、测试、制作镜像、部署**
。整个过程可以描述为代码合并到master后能够自动触发对应的集成编译，如编译通过则部署到对应的测试环境下，部署成功后驱动自动化测试，测试通过则分批部署到生产环境。主体流水线发生的状态变更，都需要通过 E-mail 通知发起人。这里的发起人就是代码提交者和合并审核人。
## 二、	流水线管理功能点介绍
### 2.1	流水线配置
当进入一个项目后，点击菜单栏的“流水线”菜单项，是流水线的入口。点击“流水线”菜单项，进入流水线列表页。此时，有“新建流水线”按钮，可添加新的流水线；有“删除流水线”按钮，可删除旧的流水线。
新建流水线时，用户需配置以下内容：
>（1）流水线名称。

>（2）流水线的管理员。仅管理员可编辑流水线。新建流水线时，当前用户被设为管理员。

>（3）监听设置。自动触发是指，在源代码的修改被推送到服务器端代码库指定分支时，触发流水线运行。另外，配置为自动触发，在流水线主页也仍然可以手工触发流水线执行。
   流水线列表的每一行，末尾有“修改”按钮，点击进入该流水线的编辑页面，可修改该流水线的配置。而如果点击流水线列表每一行左侧的流水线标题，进入该流水线的主页面后，也可以点击主页面左上角的“编辑流水线”按钮，进入该流水线的编辑页面。
### 2.2	流水线的阶段
流水线分为以下几个阶段：构建阶段、预发环境测试阶段、正式发布阶段。

每个阶段（任务）串行执行，上个阶段进行完才能进行下一个阶段。
每个阶段可选若干个阶段内执行的任务：
>   （1）构建阶段：	代码检测、单元测试、自定义脚本构建等

>   （2）预发环境测试：性能测试、集成测试、验收测试、负载测试、性能测试、冒烟测试、质量测试等
 
 >  （3）正式部署：蓝绿部署、滚动部署、灰度部署、金丝雀部署等
### 2.3	流水线的运行
#### 2.3.1	流水线的启动
可以把一条流水线设置为代码提交后**自动触发**和**手动触发**方式，即使设置为自动触发方式，也可以进行手动干预。
当设置为**自动触发**时，当前一阶段完成且后一阶段空闲时，就会自动开始运行后一阶段。当前一阶段完成时，若后一阶段正在运行，则后一阶段不受干扰，继续运行完成。待运行完成，出现空闲时，后一阶段自动启动，接续前一阶段版本运行。若在后一阶段运行时，前一阶段完成了多次运行，则在后一阶段出现空闲时，接续前一阶段的最近一次运行版本运行。
当设置为**手动触发**时，两个阶段之间出现人工触发按钮，点击开始运行后才执行下一个阶段，如果在下一阶段前，上一阶段已运行多次，则人工启动的下一阶段运行时，使用上一阶段最近一次运行的版本。
#### 2.3.2	流水线任务的中止运行
有些类型的任务，提供了**中止任务运行**的方法，相应的操作按钮出现在每个任务界面内。
#### 2.3.3	流水线的回滚
应用对应的Owner，开发负责人有权限申请回滚，进行流水线的回滚时，将选择回滚级别，系统将展示线上版本与历史版本的区别，并且操作人员将选择发布方式、发布暂停方式以及发布批次。
#### 2.3.4	流水线的监控
对整个流水线实施监控，当有bug发生时进行及时通过邮件、短信、电话等方式反馈给相对应的人员。
   
### 2.4	构建阶段
将开发人员的源代码转换为部署就绪制品，实现单个脚本或命令，使您能够将版本控制的源代码转换为单个可部署的制品。理想情况下，构建阶段需要从gitlab代码库中进行拉取代码，完成代码规范检测、单元测试等工作，完成构建，且要求如下：
>（1）能够同时支持Docker镜像的编译和集成。而且能够在触发编译时自动进行适配支持，这样才能保证各个团队有新项目时无须再进行额外配置。

>（2）所有构建产物及构建历史，都能被有效、永久地记录和存储。因为单从传统的编译驱动管理角度看，它以编译任务为基准，需要清除过久、过大的编译任务，从而释放更多的资源用于集成编译。但是，从持续交付的角度看，我们需要完全保留这些内容，用于版本追溯。
 
>（3）各构建产物有自己独立的版本体系，并与代码 commit ID 相关联。这是非常重要的，交付产物的版本就是它的唯一标识，任何交付物都可以通过版本进行辨识和追溯。

>（4）构建通道必须能够支持足够的并发量。集成构建服务要做到高可用和可扩展，最好做到资源弹性利用。
 
### 2.5	预发环境测试
调用**测试管理模块**对应用进行测试
### 2.6	正式发布
选择上述发布策略对应用进行发布。从团队交付产物的角度来看，他们需要的环境可以描述如下：测试服务器需要1个集群，2个 Docker实例；生产环境需要2个集群，各7个 Docker实例。为了发布过程更加可控，对代码目录、进程管理、日志格式等进行统一的标准化。
## 三、	功能点分析

| 功能点 | M | S | C | W | 约束 | 估时/人周 |
| :------: |:------: | :------: | :------: | :------: | :------: |:----: |
| 流水线配置 | |	Y| | | 用户管理模块|	|1|
|自动启动流水线| |Y| | | |1|
|手动启动流水线| |Y| | |	 |1|
|流水线回滚|	Y| | | |流水线配置管理|2|
|代码检出|Y| | | |代码管理服务|1|
|单元检查| | | |	Y|代码管理服务|1|
|静态代码检测| | | |Y|代码管理服务|1|
|编译|Y| | | |代码管理服务|2|
|构建镜像|Y| | | |代码管理服务|2|
|预发环境测试|Y| | | |测试管理服务|1|
|部署|Y|	 | | |支持各种发布策略|2+|
   
