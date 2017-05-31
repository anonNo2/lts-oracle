package com.github.ltsopensource.queue.mysql;

import com.github.ltsopensource.core.cluster.Config;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;
import com.github.ltsopensource.core.support.JobQueueUtils;
import com.github.ltsopensource.queue.SuspendJobQueue;
import com.github.ltsopensource.queue.domain.JobPo;
import com.github.ltsopensource.queue.mysql.support.RshHolder;
import com.github.ltsopensource.store.jdbc.builder.DeleteSql;
import com.github.ltsopensource.store.jdbc.builder.Delim;
import com.github.ltsopensource.store.jdbc.builder.SelectSql;
import com.github.ltsopensource.admin.request.JobQueueReq;

/**
 * @author bug (357693306@qq.com) on 3/4/16.
 */
public class MysqlSuspendJobQueue extends AbstractMysqlJobQueue implements SuspendJobQueue {
    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlSuspendJobQueue.class);

    public MysqlSuspendJobQueue(Config config) {
        super(config);
        createTable(readSqlFile("sql/mysql/lts_suspend_job_queue.sql", getTableName()));
    }

    @Override
    protected String getTableName(JobQueueReq request) {
        return getTableName();
    }

    @Override
    public boolean add(JobPo jobPo) {
        LOGGER.info("add, jobPo:{}" , jobPo);

        return add(getTableName(), jobPo);
    }

    @Override
    public JobPo getJob(String jobId) {
        LOGGER.info("getJob, jobId:{}" , jobId);
        return new SelectSql(getSqlTemplate())
                .select()
                .all()
                .from()
                .table(Delim.MYSQL, getTableName())
                .where("job_id = ?", jobId)
                .single(RshHolder.JOB_PO_RSH);
    }

    @Override
    public boolean remove(String jobId) {
        LOGGER.info("remove, jobId:{}" , jobId);

        return new DeleteSql(getSqlTemplate())
                .delete()
                .from()
                .table(Delim.MYSQL, getTableName())
                .where("job_id = ?", jobId)
                .doDelete() == 1;
    }

    @Override
    public JobPo getJob(String taskTrackerNodeGroup, String taskId) {
        LOGGER.info("getJob, taskId:{}" , taskId);
        return new SelectSql(getSqlTemplate())
                .select()
                .all()
                .from()
                .table(Delim.MYSQL, getTableName())
                .where("task_id = ?", taskId)
                .and("task_tracker_node_group = ?", taskTrackerNodeGroup)
                .single(RshHolder.JOB_PO_RSH);
    }

    private String getTableName() {
        return JobQueueUtils.SUSPEND_JOB_QUEUE;
    }

}
