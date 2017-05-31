package com.github.ltsopensource.queue.mysql;

import com.github.ltsopensource.admin.request.JobQueueReq;
import com.github.ltsopensource.core.cluster.Config;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;
import com.github.ltsopensource.core.support.JobQueueUtils;
import com.github.ltsopensource.queue.CronJobQueue;
import com.github.ltsopensource.queue.domain.JobPo;
import com.github.ltsopensource.queue.mysql.support.RshHolder;
import com.github.ltsopensource.store.jdbc.builder.DeleteSql;
import com.github.ltsopensource.store.jdbc.builder.Delim;
import com.github.ltsopensource.store.jdbc.builder.SelectSql;

/**
 * @author Robert HG (254963746@qq.com) on 5/31/15.
 */
public class MysqlCronJobQueue extends MysqlSchedulerJobQueue implements CronJobQueue {
    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlCronJobQueue.class);

    public MysqlCronJobQueue(Config config) {
        super(config);
        createTable(readSqlFile("sql/mysql/lts_cron_job_queue.sql", getTableName()));
    }

    @Override
    protected String getTableName(JobQueueReq request) {
        return getTableName();
    }

    @Override
    public boolean add(JobPo jobPo) {
        LOGGER.debug("[zjj] add, jobPo:{} ", jobPo);

        return super.add(getTableName(), jobPo);
    }

    @Override
    public JobPo getJob(String jobId) {
        LOGGER.debug("[zjj] getJob, jobId:{} ", jobId);
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
        LOGGER.debug("[zjj] remove, jobId:{} ", jobId);
        return new DeleteSql(getSqlTemplate())
                .delete()
                .from()
                .table(Delim.MYSQL, getTableName())
                .where("job_id = ?", jobId)
                .doDelete() == 1;
    }

    @Override
    public JobPo getJob(String taskTrackerNodeGroup, String taskId) {
        LOGGER.debug("[zjj] getJob, taskId:{} ", taskId);

        return new SelectSql(getSqlTemplate())
                .select()
                .all()
                .from()
                .table(Delim.MYSQL, getTableName())
                .where("task_id = ?", taskId)
                .and("task_tracker_node_group = ?", taskTrackerNodeGroup)
                .single(RshHolder.JOB_PO_RSH);
    }

    protected String getTableName() {
        return JobQueueUtils.CRON_JOB_QUEUE;
    }

}
