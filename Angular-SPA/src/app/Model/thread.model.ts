export interface Thread {
  id: number;
  threadTitle: string;
  threadContent_short: string;
  threadContent: string;
  threadDate: string;
  threadAuthor: string;
  threadUpvotes: number;
  threadImage: string;
  posts: any[];
}
