CREATE TABLE "lts_admin_jvm_memory" (
"id" NUMBER(11) NOT NULL PRIMARY KEY,
"gmt_created" NUMBER(20) NULL ,
"identity" NVARCHAR2(64) NULL ,
"timestamp" NUMBER(20) NULL ,
"node_type" NVARCHAR2(32) NULL ,
"node_group" NVARCHAR2(64) NULL ,
"heap_memory_committed" NUMBER(20) NULL ,
"heap_memory_init" NUMBER(20) NULL ,
"heap_memory_max" NUMBER(20) NULL ,
"heap_memory_used" NUMBER(20) NULL ,
"non_heap_memory_committed" NUMBER(20) NULL ,
"non_heap_memory_init" NUMBER(20) NULL ,
"non_heap_memory_max" NUMBER(20) NULL ,
"non_heap_memory_used" NUMBER(20) NULL ,
"perm_gen_committed" NUMBER(20) NULL ,
"perm_gen_init" NUMBER(20) NULL ,
"perm_gen_max" NUMBER(20) NULL ,
"perm_gen_used" NUMBER(20) NULL ,
"old_gen_committed" NUMBER(20) NULL ,
"old_gen_init" NUMBER(20) NULL ,
"old_gen_max" NUMBER(20) NULL ,
"old_gen_used" NUMBER(20) NULL ,
"eden_space_committed" NUMBER(20) NULL ,
"eden_space_init" NUMBER(20) NULL ,
"eden_space_max" NUMBER(20) NULL ,
"eden_space_used" NUMBER(20) NULL ,
"survivor_committed" NUMBER(20) NULL ,
"survivor_init" NUMBER(20) NULL ,
"survivor_max" NUMBER(20) NULL ,
"survivor_used" NUMBER(20) NULL 
)