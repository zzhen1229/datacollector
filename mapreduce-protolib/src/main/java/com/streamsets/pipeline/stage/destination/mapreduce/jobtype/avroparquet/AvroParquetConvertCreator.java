/*
 * Copyright 2017 StreamSets Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.pipeline.stage.destination.mapreduce.jobtype.avroparquet;

import com.streamsets.pipeline.lib.util.AvroParquetWriterBuilder190Int96;
import com.streamsets.pipeline.lib.util.AvroToParquetConverterUtil;
import com.streamsets.pipeline.stage.destination.mapreduce.MapreduceUtils;
import com.streamsets.pipeline.stage.destination.mapreduce.jobtype.avroconvert.AvroConversionBaseCreator;
import com.streamsets.pipeline.stage.destination.mapreduce.jobtype.avroconvert.AvroConversionInputFormat;
import org.apache.avro.mapred.FsInput;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.parquet.SemanticVersion;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.avro.AvroSchemaConverter190Int96;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.format.CompressionCodec;
import org.apache.parquet.hadoop.ParquetWriter;

public class AvroParquetConvertCreator extends AvroConversionBaseCreator {

  @Override
  protected void addNecessaryJarsToJob(Configuration conf) {
    MapreduceUtils.addJarsToJob(conf,
        SemanticVersion.class,
        ParquetWriter.class,
        AvroParquetWriter.class,
        AvroParquetWriterBuilder190Int96.class,
        AvroSchemaConverter190Int96.class,
        FsInput.class,
        CompressionCodec.class,
        ParquetProperties.class,
        BytesInput.class,
        AvroToParquetConverterUtil.class
    );
  }

  @Override
  protected Class<? extends InputFormat> getInputFormatClass() {
    return AvroConversionInputFormat.class;
  }

  @Override
  protected Class<? extends Mapper> getMapperClass() {
    return AvroParquetConvertMapper.class;
  }
}
