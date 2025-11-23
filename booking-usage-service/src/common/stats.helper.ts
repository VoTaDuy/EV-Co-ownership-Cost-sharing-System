import { ObjectLiteral, Repository } from "typeorm";

export class StatsHelper<T extends ObjectLiteral> {
  constructor(private repo: Repository<T>) {}

  async countTotal(): Promise<number> {
    return this.repo.count();
  }


  async countByMonth(year: number): Promise<number[]> {
    const raw = await this.repo.query(
      `
      SELECT 
        MONTH(created_at) AS month,
        COUNT(*) AS count
      FROM ${this.repo.metadata.tableName}
      WHERE YEAR(created_at) = ?
      GROUP BY MONTH(created_at)
      ORDER BY MONTH(created_at)
      `,
      [year],
    );
      // đưa về mảng 12 tháng
    const result = Array(12).fill(0);
    raw.forEach(r => result[r.month - 1] = Number(r.count));
    return result;
  }

  async countByMonthForUsage(year: number): Promise<number[]> {
    const raw = await this.repo.query(
      `
      SELECT 
        MONTH(record_time) AS month,
        COUNT(*) AS count
      FROM ${this.repo.metadata.tableName}
      WHERE YEAR(record_time) = ?
      GROUP BY MONTH(record_time)
      ORDER BY MONTH(record_time)
      `,
      [year],
    );

    const result = Array(12).fill(0);
    raw.forEach(r => result[r.month - 1] = Number(r.count));
    return result;
  }
}
