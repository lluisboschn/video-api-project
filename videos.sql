--------------------------------------------------------
--  File created - dijous-de juny-17-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table VIDEOS
--------------------------------------------------------

  CREATE TABLE "LBOSCH"."VIDEOS" 
   (	"ID" VARCHAR2(40 BYTE), 
	"NAME" VARCHAR2(40 BYTE), 
	"URL" VARCHAR2(40 BYTE), 
	"USER_ID" VARCHAR2(40 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into LBOSCH.VIDEOS
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index VIDEOS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "LBOSCH"."VIDEOS_PK" ON "LBOSCH"."VIDEOS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table VIDEOS
--------------------------------------------------------

  ALTER TABLE "LBOSCH"."VIDEOS" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "LBOSCH"."VIDEOS" MODIFY ("NAME" NOT NULL ENABLE);
 
  ALTER TABLE "LBOSCH"."VIDEOS" MODIFY ("URL" NOT NULL ENABLE);
 
  ALTER TABLE "LBOSCH"."VIDEOS" MODIFY ("USER_ID" NOT NULL ENABLE);
 
  ALTER TABLE "LBOSCH"."VIDEOS" ADD CONSTRAINT "VIDEOS_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table VIDEOS
--------------------------------------------------------

  ALTER TABLE "LBOSCH"."VIDEOS" ADD CONSTRAINT "VIDEOS_FK1" FOREIGN KEY ("USER_ID")
	  REFERENCES "LBOSCH"."USERS" ("ID") ENABLE;
